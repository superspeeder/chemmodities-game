package org.delusion.chemmodities.engine.utils;

import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileUtils {
    public static ByteBuffer loadBytesInternal(String filename) {
        ByteBuffer data;

        InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(filename);

        try {
            data = BufferUtils.createByteBuffer(stream.available());
            int b;

            while ((b = stream.read()) != -1) {
                data.put((byte)b);
            }

            return data;

        } catch (IOException e) {
            data = BufferUtils.createByteBuffer(0);
            return data;
        }
    }

    public static String readString(String path) {
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(path);

        String text = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return text;

    }
}
