package com.sch.ibeauty.repository;

import com.sch.ibeauty.entity.Gallery;
import com.sch.ibeauty.entity.Galleryclass;
import com.sch.ibeauty.entity.JsonResponse;
import com.sch.ibeauty.entity.LoginRegisterResult;
import com.sch.ibeauty.entity.PictureResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shichaohui on 16/4/13.
 * <p>
 * Api接口
 */
public interface ApiService {

    /**
     * 注册
     *
     * @param clientId     天狗云ID
     * @param clientSecret 天狗云Key
     * @param email        邮箱
     * @param account      账号
     * @param password     密码
     */
    @GET("api/oauth2/reg")
    Observable<LoginRegisterResult> register(@Query("client_id") String clientId,
                                             @Query("client_secret") String clientSecret,
                                             @Query("email") String email,
                                             @Query("account") String account,
                                             @Query("password") String password);

    /**
     * 登录
     *
     * @param clientId       天狗云ID
     * @param clientSecret   天狗云Key
     * @param accountOrEmail 账号或邮箱
     * @param password       密码
     */
    @GET("api/oauth2/login")
    Observable<LoginRegisterResult> login(@Query("client_id") String clientId,
                                          @Query("client_secret") String clientSecret,
                                          @Query("name") String accountOrEmail,
                                          @Query("password") String password);

    /**
     * 获取图库分类数据
     */
    @GET("tnfs/api/classify")
    Observable<JsonResponse<List<Galleryclass>>> loadGalleryclass();

    /**
     * 获取图库列表
     *
     * @param page 页码
     * @param rows 每页数据量
     * @param id   分类ID
     */
    @GET("tnfs/api/list")
    Observable<JsonResponse<List<Gallery>>> loadGallery(@Query("page") int page,
                                                        @Query("rows") int rows,
                                                        @Query("id") int id);

    /**
     * 获取图片列表
     *
     * @param id 图库的id
     */
    @GET("tnfs/api/show")
    Observable<PictureResponse> loadPicture(@Query("id") int id);
}
