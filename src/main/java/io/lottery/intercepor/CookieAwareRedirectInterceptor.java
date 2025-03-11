package io.lottery.intercepor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CookieAwareRedirectInterceptor implements Interceptor {
    private static final int MAX_REDIRECTS = 5;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response response = chain.proceed(originalRequest);
        int redirectCount = 0;

        while (isRedirect(response.code()) && redirectCount < MAX_REDIRECTS) {
            redirectCount++;
            response.close();

            // 获取重定向地址
            String location = response.header("Location");
            if (location == null) break;

            // 自动携带 Cookie 的关键：使用 chain.proceed() 发起新请求
            Request newRequest = originalRequest.newBuilder()
                    .url(location)
                    .method(originalRequest.method(), null) // 保留原始方法
                    .build();

            response = chain.proceed(newRequest);
        }

        if (redirectCount >= MAX_REDIRECTS) {
            throw new IOException("Too many redirects: " + redirectCount);
        }

        return response;
    }

    private boolean isRedirect(int statusCode) {
        return statusCode == 301 || statusCode == 302 || statusCode == 303
                || statusCode == 307 || statusCode == 308;
    }
}

