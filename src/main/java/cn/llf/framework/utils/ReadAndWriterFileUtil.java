package cn.llf.framework.utils;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：calvin
 * Date: 2017/8/17 0017
 */
@Data
public class ReadAndWriterFileUtil {
    private static ReadAndWriterFileUtil readFileUtil = new ReadAndWriterFileUtil();
    private ReadAndWriterFileUtil(){};
    public static ReadAndWriterFileUtil  getInstance(){
        return readFileUtil;
    }
    List<String>   listFileName = new ArrayList<String>();
    public    List<String> listAllFileName(String filePath ){
        File file = new File(filePath);
        if (file.exists()){
            File[] files = file.listFiles();
            if (files!=null && files.length>0){
             for (File temp : files){
                if (temp.isDirectory()){
                    listAllFileName(temp.getAbsolutePath());
                }else {
                    listFileName.add(temp.getName());
                }
             }
            }else {
                System.out.println(filePath+"文件夹下没有文件");
            }
        }else {
            System.out.println("【"+file.getName()+"】不存在");
        }
        return  listFileName;
    }


    public  void createTxtFileByBuffWriter(List<String> outContent,String filePath){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            for (String str:outContent){
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch (Exception e){
            System.out.println("读取文件异常："+filePath);
        }
    }


    public  void createTxtFileByOutputStreamWriter(List<String> outContent,String filePath){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath,true));
            for (String str:outContent){
                osw.write("\r\n"+str);
                osw.flush();
            }
            osw.flush();
            osw.close();
        }catch (Exception e){
            System.out.println("读取文件异常："+filePath);
        }
    }


    public List<String> readFileContent(String filePath){
        List<String> content = new ArrayList<String>();
        try {
            InputStreamReader ins = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
            BufferedReader br = new BufferedReader(ins);
            char[] row = new char[100];
            String temp;
            while ((temp = br.readLine()) != null) {
                content.add(temp);
            }
        }catch (Exception e){
            System.out.println(">>>>>读取文件异常。。。");
        }


        return  content;
    }
}
