package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor {
	
	private XMLReader reader = new XMLReader();
	private List <String> fileList;
    
	
    public Compressor() {
    	
        fileList = new ArrayList <String>();
    }

    public void createZip() {
    	
        Compressor appZip = new Compressor();
        appZip.generateFileList(new File(reader.readNode("source-folder")));
        appZip.zipIt(reader.readNode("output-zip-file"));
    }

    public void zipIt(String zipFile) {
    	
        byte[] buffer = new byte[2048];
        String source = new File(reader.readNode("source-folder")).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: this.fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(reader.readNode("source-folder") + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }
            
            zos.closeEntry();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
            	
                zos.close();
            } catch (IOException e) {
            	
                e.printStackTrace();
            }
        }
    }

    public void generateFileList(File node) {
  
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String file) {
    	
        return file.substring(reader.readNode("source-folder").length() + 1, file.length());
    }

}
