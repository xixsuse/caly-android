package io.caly.calyandroid.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.caly.calyandroid.Model.AccountModel;

/**
 * Copyright 2017 JSpiner. All rights reserved.
 *
 * @author jspiner (jspiner@naver.com)
 * @project CalyAndroid
 * @since 17. 2. 22
 */

public class AccountResponse extends BasicResponse {

    @SerializedName("payload")
    public Payload payload;

    public class Payload {

        @SerializedName("data")
        public List<AccountModel> data;

    }

}
