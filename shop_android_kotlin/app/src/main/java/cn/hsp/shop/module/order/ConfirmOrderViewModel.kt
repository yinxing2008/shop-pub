package cn.hsp.shop.module.order

import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.request.CreateOrderFromCartReq
import cn.hsp.shop.network.request.CreateOrderReq
import cn.hsp.shop.network.request.QueryOrderReq
import cn.hsp.shop.network.request.SimpleOrderInfo
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.network.response.QueryOrderResp
import cn.hsp.shop.utils.Constants

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class ConfirmOrderViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val queryOrderResp: MutableLiveData<QueryOrderResp> = MutableLiveData()

    fun createOrderFromCart(
        cartItemList: List<CartItem>,
        onSuccess: ((orderId: String) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val cartIdList = cartItemList.map { it.id }
                val createOrderResp = repo.createOrderFromCart(CreateOrderFromCartReq(cartIdList))
                onSuccess?.invoke(createOrderResp?.data?.orderId!!)
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }


    fun createOrder(
        simpleOrderInfo: SimpleOrderInfo,
        onSuccess: ((orderId: String) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val createOrderResp = repo.createOrder(CreateOrderReq(listOf(simpleOrderInfo)))
                onSuccess?.invoke(createOrderResp?.data?.orderId!!)
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun payForOrder(
        orderId: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.payForOrder(orderId)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryOrder(
        orderId: String,
        onSuccess: ((queryOrderResp: QueryOrderResp) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.queryOrder(orderId)?.data?.let { onSuccess?.invoke(it) }
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}