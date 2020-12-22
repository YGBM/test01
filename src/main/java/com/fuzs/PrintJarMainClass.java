package com.fuzs;

import java.util.jar.*;

public class PrintJarMainClass {
    public static void main(String[] args) {
        try {
          JarFile jar_file = new JarFile("/Library/apache-maven-3.6.1/.m2/repository/org/apache/phoenix/phoenix-core/5.0.0-HBase-2.0/phoenix-core-5.0.0-HBase-2.0.jar");
          if (jar_file != null) {
            Manifest manifest = jar_file.getManifest();
            if (manifest != null) {
              String value = manifest.getMainAttributes().getValue("Main-Class");
              if (value != null) {
                System.out.println(value.replaceAll("/", "."));
                return;
              }
            }
          }
        } catch (Throwable e) {
          // ignore it
        }
        System.out.println("UNKNOWN");
        System.exit(1);
      }
}
