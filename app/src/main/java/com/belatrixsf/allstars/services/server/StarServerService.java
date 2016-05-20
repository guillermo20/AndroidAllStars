package com.belatrixsf.allstars.services.server;

import com.belatrixsf.allstars.networking.retrofit.api.StarAPI;
import com.belatrixsf.allstars.networking.retrofit.requests.StarRequest;
import com.belatrixsf.allstars.networking.retrofit.responses.StarKeywordTopListResponse;
import com.belatrixsf.allstars.networking.retrofit.responses.StarsByKeywordsResponse;
import com.belatrixsf.allstars.networking.retrofit.responses.StarsResponse;
import com.belatrixsf.allstars.networking.retrofit.responses.StarResponse;
import com.belatrixsf.allstars.networking.retrofit.responses.StarSubCategoryResponse;
import com.belatrixsf.allstars.services.AllStarsBaseService;
import com.belatrixsf.allstars.services.ServiceRequest;
import com.belatrixsf.allstars.services.contracts.StarService;
import com.belatrixsf.allstars.services.ServerServiceRequest;
import com.belatrixsf.allstars.utils.AllStarsCallback;

import retrofit2.Call;

/**
 * Created by PedroCarrillo on 4/26/16.
 */
public class StarServerService extends AllStarsBaseService implements StarService {

    private StarAPI starAPI;

    public StarServerService(StarAPI starAPI) {
        this.starAPI = starAPI;
    }

    @Override
    public ServiceRequest getEmployeeSubCategoriesStars(final int employeeId, AllStarsCallback<StarSubCategoryResponse> callback) {
        Call<StarSubCategoryResponse> call = starAPI.getEmployeeSubCategoriesStars(employeeId);
        ServiceRequest<StarSubCategoryResponse> serviceRequest = new ServerServiceRequest<>(call);
        enqueue(serviceRequest, callback);
        return serviceRequest;
    }

    @Override
    public ServiceRequest star(int fromEmployeeId, int toEmployeeId, StarRequest starRequest, AllStarsCallback<StarResponse> callback) {
        Call<StarResponse> call = starAPI.star(fromEmployeeId, toEmployeeId, starRequest);
        ServiceRequest<StarResponse> serviceRequest = new ServerServiceRequest<>(call);
        enqueue(serviceRequest, callback);
        return serviceRequest;
    }

    @Override
    public ServiceRequest getStars(int employeeId, int subcategory, Integer page, AllStarsCallback<StarsResponse> callback) {
        Call<StarsResponse> call = starAPI.getStars(employeeId, subcategory, page);
        ServiceRequest<StarsResponse> serviceRequest = new ServerServiceRequest<>(call);
        enqueue(serviceRequest, callback);
        return serviceRequest;
    }

    @Override
    public ServiceRequest getStarsKeywordTopList(int keywordId, Integer page, AllStarsCallback<StarKeywordTopListResponse> callback) {
        Call<StarKeywordTopListResponse> call = starAPI.getStarsKeywordTop(keywordId, page);
        ServiceRequest<StarKeywordTopListResponse> serviceRequest = new ServerServiceRequest<>(call);
        enqueue(serviceRequest, callback);
        return serviceRequest;
    }

    @Override
    public ServiceRequest getStarsByKeywords(String search, Integer next, AllStarsCallback<StarsByKeywordsResponse> callback) {
        Call<StarsByKeywordsResponse> call = starAPI.getStarsByKeywords(search, next);
        ServiceRequest<StarsByKeywordsResponse> serviceRequest = new ServerServiceRequest<>(call);
        enqueue(serviceRequest, callback);
        return serviceRequest;
    }
}