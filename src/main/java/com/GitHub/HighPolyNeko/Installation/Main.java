package com.GitHub.HighPolyNeko.Installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        File Game = new File("C://Game");
        createDirectories(Game, new String[]{"src", "res", "savegames", "temp"}, result);

        for (File item : Game.listFiles()) {
            switch (item.getName()) {
                case "src" -> {
                    createDirectory(new File(item, "main"), "src/main", result);
                    createDirectory(new File(item, "test"), "src/test", result);

                    File main = new File(item, "main");
                    if (main.exists()) {
                        createFile(new File(main, "main.java"), "src/main/main.java", result);
                        createFile(new File(main, "Utils.java"), "src/main/Utils.java", result);
                    }
                }
                case "res" -> {
                    createDirectory(new File(item, "drawables"), "res/drawables", result);
                    createDirectory(new File(item, "vectors"), "res/vectors", result);
                    createDirectory(new File(item, "icons"), "res/icons", result);
                }
            }
        }

        createTempFile(Game, result);
    }

    private static void createDirectories(File parent, String[] dirs, StringBuilder result) {
        for (String dir : dirs) {
            createDirectory(new File(parent, dir), dir, result);
        }
    }

    private static void createDirectory(File dir, String name, StringBuilder result) {
        result.append("Директория ").append(name)
                .append(dir.mkdir() ? " Создана" : " Не создана").append("\n");
    }

    private static void createFile(File file, String name, StringBuilder result) {
        try {
            result.append("Файл ").append(name)
                    .append(file.createNewFile() ? " Создан" : " Не создан").append("\n");
        } catch (IOException e) {
            System.out.println("Ошибка создания файла: " + e.getMessage());
        }
    }

    private static void createTempFile(File gameDir, StringBuilder result) {
        File temp = new File(gameDir, "temp");
        if (temp.exists()) {
            File tempFile = new File(temp, "temp.txt");
            createFile(tempFile, "temp/temp.txt", result);

            if (tempFile.exists()) {
                try (FileWriter writer = new FileWriter(tempFile, false)) {
                    writer.write(result.toString());
                } catch (IOException ex) {
                    System.out.println("Ошибка записи в файл: " + ex.getMessage());
                }
            }
        }
    }
}
