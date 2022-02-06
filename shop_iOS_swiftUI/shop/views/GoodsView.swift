// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI


struct GoodsView: View {
    var goods: Goods
    var body: some View {
        VStack{
            Text(goods.name)
                .padding()
            Spacer()
            HStack{
                Spacer()
                Button(action:{ print("加购物车啦")}){
                    Text("购物车").foregroundColor(Color(hex: 0x737373))
                    Image(systemName: "cart").foregroundColor(Color(hex: 0x737373))
                }
    
                Spacer()
                Button(action:{ print("我被点啦")}){
                    Text("购买").font(.headline).frame(minWidth: 150)
                }
                .padding(EdgeInsets.init(top: 5, leading: 0, bottom: 5, trailing: 0))
                .foregroundColor(.white)
                .background(Color.main_color)
                .clipShape(RoundedRectangle(cornerRadius: 5))
                Spacer()
            }.padding()
        }
    }
}


struct GoodsView_Previews: PreviewProvider {
    static var previews: some View {
        GoodsView(goods: Goods())
    }
}
