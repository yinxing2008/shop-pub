package cn.lblbc.shop.module.login

import android.provider.SyncStateContract
import cn.lblbc.shop.utils.SP_KEY_TOKEN
import cn.lblbc.shop.utils.SpUtil

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
object LoginManager {
    fun isLoggedIn() = !SpUtil.get(SP_KEY_TOKEN, "").isNullOrEmpty()
    fun quitLogin() = SpUtil.remove(SP_KEY_TOKEN)
}