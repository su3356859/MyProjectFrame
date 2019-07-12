package com.demo.android.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.demo.android.app.AppContext;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suxiaohui on 17/11/9.
 */
public class RetrofitClient {
    private static Retrofit mRetrofit;

    private RetrofitClient() {

    }

    public static Retrofit builderRetrofit() {
        if (mRetrofit == null) {
            // log
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullToDefaultValueAdapterFactory()).create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(AppContext.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkhttp().build())
                    .build();
        }
        return mRetrofit;
    }

    public static OkHttpClient.Builder getOkhttp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println("RetrofitLog:   " + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient;
    }


    public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }


    public static class NullToDefaultValueAdapterFactory<T> implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType == String.class) {
                return (TypeAdapter<T>) new StringNullAdapter();
            } else if (rawType == Boolean.class) {
                return (TypeAdapter<T>) new BooleanNullAdapter();
            } else if (rawType == Integer.class) {
                return (TypeAdapter<T>) new IntegerNullAdapter();
            } else if (rawType == Double.class) {
                return (TypeAdapter<T>) new DoubleNullAdapter();
            } else if (rawType == Long.class) {
                return (TypeAdapter<T>) new LongNullAdapter();
            }
            return null;
        }
    }

    public static class LongNullAdapter extends TypeAdapter<Long> {
        @Override
        public Long read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0l;
            }
            return reader.nextLong();
        }

        @Override
        public void write(JsonWriter writer, Long value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class DoubleNullAdapter extends TypeAdapter<Double> {
        @Override
        public Double read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0.0d;
            }
            double doubleValue = 0.0d;
            try {
                String stringValue = reader.nextString();
                doubleValue = Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                return 0.0d;
            }
            return doubleValue;
        }

        @Override
        public void write(JsonWriter writer, Double value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class IntegerNullAdapter extends TypeAdapter<Integer> {
        @Override
        public Integer read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0;
            }
            return reader.nextInt();
        }

        @Override
        public void write(JsonWriter writer, Integer value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class BooleanNullAdapter extends TypeAdapter<Boolean> {
        @Override
        public Boolean read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return false;
            }
            return reader.nextBoolean();
        }

        @Override
        public void write(JsonWriter writer, Boolean value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }
}
