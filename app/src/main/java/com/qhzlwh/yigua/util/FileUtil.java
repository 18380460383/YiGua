package com.qhzlwh.yigua.util;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 创建者：Administrator
 * 时间：2016/9/1
 * 功能描述：
 */
public class FileUtil {
    public static String fileName = "currencyHtml.html";
    public static String PATH= Environment.getExternalStorageDirectory()+File.separator+"rawHtml"+File.separator;
    public static String getFromRaw(Context context, int id){
        String line="";
        String Result="";
        try {
            InputStreamReader inputReader = new InputStreamReader( context.getResources().openRawResource(id));
            BufferedReader bufReader = new BufferedReader(inputReader);

            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }
    public String getFileRaw(Context context,int id) throws IOException{
        String res = "";
        try{
            //得到资源中的Raw数据流
            InputStream in = context.getResources().openRawResource(id);

            //得到数据的大小
            int length = in.available();
            byte [] buffer = new byte[length];
            //读取数据
            in.read(buffer);
            //依test.txt的编码类型选择合适的编码，如果不调整会乱码
            res = EncodingUtils.getString(buffer, "UTF-8");

            //关闭
            in.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    //读文件
    public String readSDFile(String fileName) throws IOException {
        String res="";
        File file = new File(fileName);

        FileInputStream fis = new FileInputStream(file);

        int length = fis.available();

        byte [] buffer = new byte[length];
        fis.read(buffer);

        res = EncodingUtils.getString(buffer, "UTF-8");

        fis.close();
        return res;
    }

    //写文件
    public static void writeSDFile(String write_str) throws IOException{

        File file1 = new File(PATH);
        if(!file1.exists()){
            file1.mkdirs();
        }
        File file=new File(PATH+fileName);
        file.createNewFile();
        //File file=new File(R.raw.currencyHtml);

        FileOutputStream fos = new FileOutputStream(file);

        byte [] bytes = write_str.getBytes();

        fos.write(bytes);

        fos.close();
        LogUtil.e("file", "保存成功");
    }
    /**
     *@author chenzheng_Java
     *保存用户输入的内容到文件
     */
    public static void save(Context context, String fileName1,String dataStr) {

        String content = dataStr;
        try {
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
             *          该文件会被保存在/data/data/应用名称/files/chenzheng_java.txt
             * 第二个参数，代表文件的操作模式
             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
             *          MODE_WORLD_READABLE 公用  可读
             *          MODE_WORLD_WRITEABLE 公用 可读写
             *  */
            File file=new File("/data/data/com.qhzlwh.yigua/"+fileName1);
            if(!file.exists()){
                LogUtil.e("file","文件不存在");
                file.mkdirs();
            }
            FileOutputStream outputStream = context.openFileOutput(fileName1,
                    Activity.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
            LogUtil.e("file","保存成功");
            //Toast.makeText(FileTest.this, "保存成功", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author chenzheng_java
     * 读取刚才用户保存的内容
     */
    public static String read(Context context,String fileName1) {
        String content="";
        try {
            FileInputStream inputStream = context.openFileInput(fileName1);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            content = new String(arrayOutputStream.toByteArray());
            LogUtil.e("file", "文件打开成功："+content);
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public static String getFileName(String pathandname){

        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return pathandname.substring(start+1,pathandname.length());
        }else{
            return null;
        }

    }


}
