package com.ist.apachepoidoc;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 200;

    Button btnCreate, btnExcel;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkStoragePermission();

        viewInitialize();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWord(editText.getText().toString());
            }
        });

        btnExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createExcel(this,editText.getText().toString());
            }
        });

    }

    public void viewInitialize(){
        btnCreate = findViewById(R.id.createWord);
        editText = findViewById(R.id.edittext);
        btnExcel = findViewById(R.id.createExcel);
    }

    private boolean createExcel(View.OnClickListener context, String fileName) {

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIME.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("myOrder");

        for (int rw=0; rw<10; rw++){
            // Generate column headings
            Row row = sheet1.createRow(rw);
            for (int i =0; i<10; i++){
                c = row.createCell(i);
                c.setCellValue("Item Number" + rw +"-"+i);
                c.setCellStyle(cs);

                sheet1.setColumnWidth(i, (15 * 500));
            }
        }

        /*c = row.createCell(0);
        c.setCellValue("Item Number");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Quantity");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Price");
        c.setCellStyle(cs);*/

        /*sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));*/

        // Create a path where we will place our List of objects on external storage
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/myExcel/";

        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }


        Random random = new Random();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String requiredDate = df.format(new Date());

        // Obtain a number between [0 - 49].
        int n = random.nextInt(50);

        String randomText = requiredDate + "_" + n;

        String targetExcel = directory_path+"excel_"+randomText+".xls";
        System.out.println(targetExcel);
        Toast.makeText(this, targetExcel, Toast.LENGTH_LONG).show();
        File filePath = new File(targetExcel);

        FileOutputStream os = null;

        try {
            os = new FileOutputStream(filePath);
            wb.write(os);
            Toast.makeText(this, "Excel File Created", Toast.LENGTH_LONG).show();
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + directory_path, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    public void createWord(String sometext){

        try {
            XWPFDocument document = new XWPFDocument();


            // write the document content
            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mydoc/";

            File file = new File(directory_path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String targetPdf = directory_path+"word.docx";
            File filePath = new File(targetPdf);
            FileOutputStream out = new FileOutputStream(filePath);
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            run.setText(sometext);
            document.write(out);
            out.close();

            Toast.makeText(this, "Word File Created", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error: "+e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
    }

    public void checkStoragePermission() {

        final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(MainActivity.this,
                    PERMISSIONS,
                    MY_PERMISSIONS_REQUEST_STORAGE);

        } else {
            //permission already granted
            //like android version < 5(lollipop) don,t need runtime permission
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        //permission granted
                    }

                } else {
                    permissionDenied();
                }
                return;
            }

        }
    }

    private void permissionDenied() {
        Toast.makeText(this, "Denied",Toast.LENGTH_SHORT).show();

    }
}
