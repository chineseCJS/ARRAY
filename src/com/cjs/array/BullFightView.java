package com.cjs.array;
import com.cjs.array.BullFightModel;
import com.cjs.common.util.DoubleChange;
import com.cjs.common.util.DuQuUtil;
public class BullFightView {

	public static void main(String[] args) {
		bullBullView();
	}
	
	
	public static void bullBullView() {
		// 欢迎语
		System.out.println("欢迎使用陈氏斗牛牛");
		System.out.println("您获得启动资金1000万元，祝您玩得愉快");
		System.out.println("");
		// 用户开牌
		double ii = 1000;
		while (true) {
			System.out.println("您的手牌如下:");
			int name1=BullFightModel.bullBullModel();
			// 用户输入赌注金额(万)
			double money = wager(ii);
			// 庄家开牌
			System.out.println("");
			System.out.println("庄家的手牌如下:");
			int name2=BullFightModel.bullBullModel();
			System.out.println("");
			// 牌局结果
			double kk = compare(name1, name2, money, ii);
			ii = kk;
			// 判断是否继续
			if (kk == 0) {
				System.out.println("没钱就别玩了");
				break;
			} else {
				String yyn = yesOrNo();
				if (yyn.equalsIgnoreCase("n")) {
					System.out.println("游戏结束");
					System.out.println("你将带着" + ii + "元离开");
					break;
				}
			}
		}
	}
	
	public static double compare(int a,int b,double d, double ii) {
		int ss = 0;
		if(a>b) {
			System.out.println("恭喜！你赢了:");
			ii = ii + d;
			ss = 1;
			System.out.println("你目前有" + ii + "万元");
		}if(a<b) {
			System.out.println("可惜！你输了:");
			ii = ii - d;
			System.out.println("你目前有" + ii + "万元");
			if (ii == 0) {
				System.out.println("倾家荡产!");
				ss = 2;
			}
		}if(a==b) {
			double tt=Math.random();
			if(tt>=0.5) {
				System.out.println("恭喜！你赢了:");
				ii = ii + d;
				ss = 1;
				System.out.println("你目前有" + ii + "万元");
			}else {
				System.out.println("可惜！你输了:");
				ii = ii - d;
				System.out.println("你目前有" + ii + "万元");
				if (ii == 0) {
					System.out.println("倾家荡产!");
					ss = 2;
				}
			}
		}
		return ii;
	}
	
	public static double wager(double ii) {
		while (true) {
			System.out.println("");
			System.out.println("目前拥有" + ii + "万元");
			String a = DuQuUtil.readDouble("下注（单位：万）:>请注意，下多少赢(输)多少", -1);
			try {
				double money = DoubleChange.zhuanHuan(a);
				if (money <= ii && money > 0) {
					return money;
				}
			} catch (IllegalArgumentException e) {
				System.out.println("输入错误，重新输入");
			}
		}
	}
	
	public static String yesOrNo() {
		while (true) {
			try {
				String yn = DuQuUtil.readLine("是否继续赌局:Y/N:>除输入N(n)外其他任何情况默认继续,谨慎选择!", null);
				return yn;
			} catch (IllegalArgumentException e) {
				System.out.println("输入错误，重新输入");
			} catch (NullPointerException ee) {
				System.out.println("输入错误，重新输入");
			}
		}
	}

}
