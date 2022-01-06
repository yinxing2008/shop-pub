package cn.hsp.shop.module.cart

import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.response.Goods

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class CartViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val dataList: MutableLiveData<List<Goods>> = MutableLiveData()

    fun queryGoods(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                dataList.value = repo.queryCart()?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}