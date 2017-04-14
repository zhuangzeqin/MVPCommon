package com.eeepay.cn.mvp.demo.api;

import com.eeepay.cn.mvp.demo.bean.Contributors;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-17:08
 * 邮箱：zzq@eeepay.cn
 */
public interface GitServiceApi {
    String BASEURL = "https://api.github.com/";

    @GET("repos/{owner}/{repo}/contributors")
//    Call<List<Contributors>> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
    Observable<List<Contributors>> contributorsBySimpleGetCall(@Path(value = "owner",encoded = true) String owner, @Path("repo") String repo);
//    Subscriber<List<Contributors>> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
//    Call<BaseCallback<List<Contributors>>> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);

    //使用@Url注解，传入该Url地址就OK啦，跨过BaseUrl，直接访问该Url地址
    @GET
    Call<String> getNewsList(@Url String url);


    //如果想用表单 @FieldMap
    @FormUrlEncoded //Url编码
    @POST("/url")
    Call<ResponseBody> postForm(@FieldMap Map<String , Object> maps);


    //如果直接用对象 @Body
    @POST("url")
    Call<ResponseBody> PostBody(@Body Objects objects);


    //如果直接多参数 @QueryMap

    @PUT("/url")
    Call<ResponseBody> queryMap(@QueryMap Map<String, String> maps);


    //如果上传文件 @Part

    @Multipart
    @POST("/url")
    Call<ResponseBody> uploadFlie(
            @Part("description") RequestBody description,
            @Part("files") MultipartBody.Part file);


    //如果多文件上传 @PartMap()

    @Multipart
    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path("url") String url,
            @PartMap() Map<String, RequestBody> maps);

    //下载文件
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

//    @DELETE
//    @PUT
//    @POST
//    @GET
    //常用的注解的使用
//    @Query、@QueryMap：用于Http Get请求传递参数
//    @Field：用于Post方式传递参数,需要在请求接口方法上添加@FormUrlEncoded,即以表单的方式传递参数
//    @Body：用于Post,根据转换方式将实例对象转化为对应字符串传递参数.比如Retrofit添加GsonConverterFactory则是将body转化为gson字符串进行传递
//    @Path：用于URL上占位符
//    @Part：配合@Multipart使用,一般用于文件上传
//    @Header：添加http header
//    @Headers：跟@Header作用一样,只是使用方式不一样,@Header是作为请求方法的参数传入,@Headers是以固定方式直接添加到请求方法上
}
