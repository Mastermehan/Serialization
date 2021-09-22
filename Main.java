import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        List<File> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>();

        addDir(dirs);
        addFile(files);

        String dirLog = mkdir(dirs);
        String fileLog = createNewFile(files);

        FileWriter(dirLog);
        FileWriter(fileLog);

        GameProgress progress1 = new GameProgress(122, 12, 4, 2324.32);
        GameProgress progress2 = new GameProgress(133, 23, 4, 23.123);
        GameProgress progress3 = new GameProgress(3213, 11, 6, 32.32);

        List<File> fileSave = new ArrayList<>();
        fileOutputStream(fileSave);

        saveGame(fileSave.get(0).getAbsolutePath(), progress1);
        saveGame(fileSave.get(1).getAbsolutePath(), progress2);
        saveGame(fileSave.get(2).getAbsolutePath(), progress3);

        String zipName = "C://Gaming/savegames/save.zip";

        fileOutputStream(fileSave);

        zipFile(zipName, fileSave);
        deleteFile(fileSave);

    }

    private static void addDir(List<File> dirs) {
        dirs.add(new File("C://Gaming"));
        dirs.add(new File("C://Gaming", "src"));
        dirs.add(new File("C://Gaming", "res"));
        dirs.add(new File("C://Gaming", "savegames"));
        dirs.add(new File("C://Gaming", "temp"));
        dirs.add(new File("C://Gaming/src", "main"));
        dirs.add(new File("C://Gaming/src", "test"));
        dirs.add(new File("C://Gaming/res", "drawables"));
        dirs.add(new File("C://Gaming/res", "vectors"));
        dirs.add(new File("C://Gaming/res", "icons"));
    }

    private static void addFile(List<File> files) {
        files.add(new File("C://Gaming/src/main//Main.java"));
        files.add(new File("C://Gaming/src/main//Utils.java"));
        files.add(new File("C://Gaming/temp/temp.txt"));
    }

    private static String mkdir(List<File> dirs) {
        StringBuilder sb = new StringBuilder();
        for (File dir : dirs) {
            if (dir.mkdir())
                sb
                        .append("Каталог ")
                        .append(dir.getName())
                        .append("  создан")
                        .append("\n");
        }
        return sb.toString();
    }

    private static String createNewFile(List<File> files) {
        StringBuilder bold = new StringBuilder();
        for (File file : files) {
            try {
                if (file.createNewFile())
                    bold
                            .append("Файл ")
                            .append(file.getName())
                            .append("  создан")
                            .append("\n");
            } catch (IOException ex) {
                bold
                        .append(ex.getMessage())
                        .append("\n");
            }
        }
        return bold.toString();
    }

    private static void FileWriter(String text) {
        try (FileWriter writer = new FileWriter("C://Gaming/temp/temp.txt", true)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Размер файла: " + text.length());
    }

    // тут начало 2 задачи

    private static void fileOutputStream(List<File> fileSave) {
        fileSave.add(new File("C://Gaming/savegames/save1.dat"));
        fileSave.add(new File("C://Gaming/savegames/save2.dat"));
        fileSave.add(new File("C://Gaming/savegames/save3.dat"));
    }

    private static void saveGame(String fileSave, GameProgress progresses) {
        try (FileOutputStream fos = new FileOutputStream(fileSave);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progresses);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFile(String zipName, List<File> fileSave) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipName))) {
            int count = 1;
            for (File fileName : fileSave) {
                FileInputStream fis = new FileInputStream(fileName.getAbsolutePath());
                ZipEntry entry = new ZipEntry("save" + count++ + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFile(List<File> fileSave) {
        for (File fileSaves : fileSave) {
            if (fileSaves.delete())
                System.out.println("файлы сохранений удалены");
        }

    }
}


//















