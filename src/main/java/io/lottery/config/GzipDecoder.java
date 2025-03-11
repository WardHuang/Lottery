package io.lottery.config;

import feign.Response;
import feign.codec.Decoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.zip.GZIPInputStream;

public class GzipDecoder implements Decoder {

    private final Decoder defaultDecoder;

    // 构造函数，传入默认的解码器
    public GzipDecoder(Decoder defaultDecoder) {
        this.defaultDecoder = defaultDecoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        // 检查 Content-Encoding 是否为 gzip
        if ("gzip".equalsIgnoreCase(
                response.headers().getOrDefault("Content-Encoding", Collections.emptyList())
                        .stream().findFirst().orElse(""))
        ) {
            // 如果是 gzip，则解压数据
            InputStream gzipInputStream = new GZIPInputStream(response.body().asInputStream());
            // 读取解压缩后的数据
            byte[] decompressedData = toByteArray(gzipInputStream);
            // 使用解压后的数据重新构造 Response
            Response newResponse = response.toBuilder()
                    .body(decompressedData)
                    .build();
            // 使用默认的解码器来处理解压后的响应
            return defaultDecoder.decode(newResponse, type);
        }
        // 如果没有 gzip 编码，直接使用默认的解码器
        return defaultDecoder.decode(response, type);
    }

    // 帮助方法：将 InputStream 转换为 byte[]
    private byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
