package com.GitHub.HighPolyNeko.SaveAndDownload;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String path = "C://Game/savegames/";

        GameProgress game1 = new GameProgress(100, 10, 1, 0.0);
        GameProgress game2 = new GameProgress(90, 8, 2, 50.5);
        GameProgress game3 = new GameProgress(70, 5, 3, 120.2);

        // Сохраняем эти объекты в файлы
        saveGame(path + "save1.dat", game1);
        saveGame(path + "save2.dat", game2);
        saveGame(path + "save3.dat", game3);

        // Создаем список файлов для архивации
        ArrayList<String> saves = new ArrayList<>();
        saves.add(path + "save1.dat");
        saves.add(path + "save2.dat");
        saves.add(path + "save3.dat");

        // Архивируем файлы
        zipFiles(path + "saves.zip", saves);

        // Удаляем исходные файлы сохранений
        for (String save : saves) {
            new File(save).delete();
        }

        // Обратно разархивируем файлы сохранений
        openZip(path + "saves.zip", path);

        for (File item : new File(path).listFiles()) {
            if (item.getName().contains(".dat")) {
                System.out.println(openProgress(item.getPath()).toString());
            }
        }
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static GameProgress openProgress(String save) {
        GameProgress progress = null;
        if (new File(save).exists()) {
            try (FileInputStream fis = new FileInputStream(save);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                progress = (GameProgress) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return progress;
    }

    private static void zipFiles(String path, ArrayList<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file);
                    zout.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void openZip(String zipPath, String outPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                String[] pathPlusName = entry.getName().split("/");
                name = pathPlusName[pathPlusName.length - 1];
                FileOutputStream fout = new FileOutputStream(outPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
