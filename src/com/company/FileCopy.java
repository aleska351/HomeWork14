package com.company;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

/*
Размер файла 1.43GB. Путь к файлу источника и приемника задается через параметры командной строки.
 */
public class FileCopy {
    public static void main(String[] args) {

        //copyBytes(args[0], args[1]);
        //copyBuffer(args[0], args[1]);
        //copyBufferedByte(args[0], args[1]);
        copyBufferedBuffer(args[0], args[1]);
    }

    /**
     * Copying a large file (more than 1GB) using InputStream and OutputStream.
     * Byte read and write directly from / to File [Input / Output] Stream
     *
     * @param inputFile  -link to the file that is being copied.
     * @param outputFile - link to the file to be copied.
     */
    /*
           4756 секунд - файл 1.43 Gb = примерно 2.75 Мегабайт в секунду
    */
    private static void copyBytes(String inputFile, String outputFile) {
        // long start = System.currentTimeMillis();
        long start = System.nanoTime();
        try (InputStream input = new FileInputStream(inputFile);
             OutputStream output = new FileOutputStream(outputFile);) {
            while (true) {
                int readByte = input.read();
                if (readByte != -1) {
                    output.write(readByte);
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.println(timeElapsed);
                } else return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long finish = System.nanoTime();
            //long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed / 1000);
        }
    }

    /**
     * Copying a large file (more than 1GB) using InputStream and OutputStream.
     * Buffered (local byte array of 4Kb) read and write directly from / to File [Input / Output] Stream.
     *
     * @param inputFile  -link to the file that is being copied.
     * @param outputFile - link to the file to be copied.
     */
    /*
    Данные с жесткого диска типа HDD /
    520 секунд - файл 1.43 Gb = примерно 2.75 Мегабайт в секунду
    68 секунд - файл 1.43 Gb = примерно 21.0 Мегабайт в секунду с размером буффера 8192 байт
    67 секунд - файл 1.43 Gb = примерно 21.35 Мегабайт в секунду с размером буффера 16384 байт
    60 секунд - файл 1.43 Gb = примерно 23.83 Мегабайт в секунду с размером буффера 32768 байт
    56 секунд - файл 1.43 Gb = примерно 18.33 Мегабайт в секунду с размером буффера 65536  байт
    */
    private static void copyBuffer(String inputFile, String outputFile) {
        long start = System.nanoTime();
        //long start = System.currentTimeMillis();
        try (InputStream input = new FileInputStream(inputFile);
             OutputStream output = new FileOutputStream(outputFile);) {
            byte[] buffer = new byte[65536];
            while (true) {
                int readCount = input.read(buffer);
                if (readCount != -1) {
                    output.write(buffer, 0, readCount);
                } else return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long finish = System.nanoTime();
            //long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed / 1000);
        }
    }

    /**
     * Copying a large file (more than 1GB) using InputStream and OutputStream.
     * Byte read and write  through Buffered [Input / Output] Stream
     *
     * @param inputFile  -link to the file that is being copied.
     * @param outputFile - link to the file to be copied.
     */
    /*
    Данные с жесткого диска типа HDD /
    344 секунд - файл 1.43 Gb = примерно 4.15 Мегабайт в секунду
    63 секунд - файл 1.43 Gb = примерно 22.7 Мегабайт в секунду с размером буффера 8192 байт
    58 секунд - файл 1.43 Gb = примерно 24.65 Мегабайт в секунду с размером буффера 16384 байт
    78 секунд - файл 1.43 Gb = примерно 18.33 Мегабайт в секунду с размером буффера 32768 байт
    80 секунд - файл 1.43 Gb = примерно 17.89 Мегабайт в секунду с размером буффера 65536 байт
     */
    private static void copyBufferedByte(String inputFile, String outputFile) {
        long start = System.nanoTime();
        //  long start = System.currentTimeMillis();
        try (InputStream input = new BufferedInputStream(new FileInputStream(inputFile));
             OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            byte[] buffer = new byte[65536];
            while (true) {
                int readCount = input.read(buffer);
                if (readCount != -1) {
                    output.write(buffer, 0, readCount);
                } else return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long finish = System.nanoTime();
            // long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed / 1000);
        }
    }

    /**
     * Copying a large file (more than 1GB) using InputStream and OutputStream.
     * Buffer (local byte array of 4K) reading and writing via Buffered [Input / Output] Stream.
     *
     * @param inputFile  -link to the file that is being copied.
     * @param outputFile - link to the file to be copied.
     */
    /*
    Данные с жесткого диска типа HDD /
    121 секунд - файл 1.43 Gb = примерно 11.1 килобайт в секунду с размером буффера 4096 байт
    67 секунд - файл 1.43 Gb = примерно 21.2 Мегабайт в секунду с размером буффера 8192 байт
    58 секунд - файл 1.43 Gb = примерно 24.65 Мегабайт в секунду с размером буффера 16384 байт
    67 секунд - файл 1.43 Gb = примерно 21.2 Мегабайт в секунду с размером буффера 32768 байт
    68 секунд - файл 1.43 Gb = примерно 21.0 Мегабайт в секунду с размером буффера 65536 байт
     */
    private static void copyBufferedBuffer(String inputFile, String outputFile) {
        Instant start = Instant.now();
        //  long start = System.currentTimeMillis();
        try (InputStream input = new BufferedInputStream(new FileInputStream(inputFile));
             OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {

            byte[] buffer = new byte[65536];
            while (true) {
                int readCount = input.read(buffer);
                if (readCount != -1) {
                    output.write(buffer, 0, readCount);

                } else return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //   long finish = System.currentTimeMillis();
            Instant finish = Instant.now();
            //  long timeElapsed = finish - start;
            long timeElapsed = Duration.between(start, finish).toMillis();
            System.out.println(timeElapsed / 1000);
        }
    }
}