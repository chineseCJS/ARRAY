package com.cjs.array;

import java.util.Arrays;

public class BullFightModel {

	public static void main(String[] args) {
		bullBullModel();
	}
	
	
	public static int bullBullModel() {
		int[] porker=porker();
		shuffle(porker);
		int[] cards=subArray(porker, 0, 5);
		String cardsDesc=getCardsDescription(cards);
		System.out.println("手牌:"+cardsDesc);
		int cc=cardType(cards);
	    String name=nine(cc);
	    System.out.println("牌型:"+name);
	    return cc;
	}

	
	/**
	 * 创造一副扑克牌
	 * 返回一副扑克牌 一个3位整数表示一张牌，百位表示花色 十位和个位表示点数 4xx 黑桃 3xx 红桃 2xx 梅花 1xx 方块 A=1 2.。 J=11
	 * Q=12 K=13 黑桃5=405 大王=999 小王=888
	 * 
	 * @return
	 */
	public static int[] porker() {
		int[] porker = new int[52];
		int i = 0;
		for (int color = 100; color <= 400; color += 100) {
			for (int dot = 14; dot-- > 1;) {
				porker[i++] = color + dot;
			}
		}
		return porker;
}
	/**
	 * 洗牌
	 */
	public static void shuffle(int[] porker) {
		for (int i=porker.length; i-- > 0;) {
			int ranIndex = (int) (Math.random() * porker.length);
			porker[0] = porker[ranIndex] + (porker[ranIndex] = porker[0]) * 0;
		}
	}
	
	/**
	 * 发牌 [1,2,3,4,5,6] 1~3 ==>[2,3]
	 * 
	 */
	public static int[] subArray(int[] arr,int begin, int end) {
		int[] result = new int[end - begin];
		int index = 0;
		for (int i = begin; i < end; i++) {
			result[index++] = arr[i];
		}
		return result;
	}
	
    //显示牌面
	private static String getCardsDescription(int[] cards) {
		String result="";
		for(int i=0;i<cards.length;i++) {
			result+=getCardDescription(cards[i]);
		}
		return result;
	}
	private static String getCardDescription(int card) {
		int color=card/100;
		int dot=card%100;
		return getColorDescription(color)+getDotDescription(dot);
	}
	private static String getColorDescription(int color) {
		return new String[] {"♦","♣","♥","♠"}[color-1];
	}
	private static String getDotDescription(int dot) {
		switch (dot) {
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";	
		default:
			return dot+"";
		}
	}
	
	//显示牌型
	public static String nine(int a) {
		String[] arr= {"没分","有分","牛牛","四花","五花","五小","炸弹"};
		return arr[a-1];
	}
	
	
	//判断牌型
	public static int cardType(int[] cards) {
		if(isBomb(cards)){
			return 7;
		}if(isfiveSmall(cards)){
			return 6;
		}if(isFiveFlower(cards)){
			return 5;
		}if(isFourFlower(cards)){
			return 4;
		}if(isBullBull(cards)){
			return 3;
		}if(isHaveBull(cards)){
			return 2;
		}else{
			return 1;
		}
	}
	
	//J K Q 换成10
	public static int ten(int a) {
		switch (a) {
		case 11:
			a=10;
			break;
		case 12:
			a=10;
			break;
		case 13:
			a=10;
			break;
		}
		return a;
	}
	
	//点数排序
	public static int[] one(int[] a) {
		int dot1=a[0]%100;
		int dot2=a[1]%100;
		int dot3=a[2]%100;
		int dot4=a[3]%100;
		int dot5=a[4]%100;
		int[] aa=new int[] {dot1,dot2,dot3,dot4,dot5};
		Arrays.sort(aa);
		a=aa;
		return a;
	}
	
	public static boolean isBomb(int[] cards){
		cards=one(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		int dot4=cards[3]%100;
		int dot5=cards[4]%100;
		return  (dot1==dot2&&dot2==dot3&&dot3==dot4)||(dot2==dot3&&dot3==dot4&&dot4==dot5);
		}
	
	public static boolean isfiveSmall(int[] cards) {
		cards=one(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		int dot4=cards[3]%100;
		int dot5=cards[4]%100;
		return dot1<5&&dot2<5&&dot3<5&&dot4<5&&dot5<5&&(dot1+dot2+dot3+dot4+dot5)<=10;
	}
	
	public static boolean isFiveFlower(int[] cards) {
		cards=one(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		int dot4=cards[3]%100;
		int dot5=cards[4]%100;
		return dot1>10&&dot2>10&&dot3>10&&dot4>10&&dot5>10;
	}
	
	public static boolean isFourFlower(int[] cards) {
		cards=one(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		int dot4=cards[3]%100;
		int dot5=cards[4]%100;
		return dot2>10&&dot3>10&&dot4>10&&dot5>10;
	}
	
	public static boolean isBullBull(int[] cards) {
		cards=one(cards);
		int dot1=cards[0]%100;
		dot1=ten(dot1);
		int dot2=cards[1]%100;
		dot2=ten(dot2);
		int dot3=cards[2]%100;
		dot3=ten(dot3);
		int dot4=cards[3]%100;
		dot4=ten(dot4);
		int dot5=cards[4]%100;
		dot5=ten(dot5);
		return ((dot1+dot2+dot3)%10==0&&(dot4+dot5)%10==0)||((dot2+dot3+dot4)%10==0&&(dot1+dot5)%10==0)
				||((dot3+dot4+dot5)%10==0&&(dot1+dot2)%10==0)||((dot4+dot5+dot1)%10==0&&(dot2+dot3)%10==0)
				||((dot5+dot1+dot2)%10==0&&(dot4+dot3)%10==0)||((dot4+dot2+dot5)%10==0&&(dot1+dot3)%10==0)
				||((dot5+dot2+dot3)%10==0&&(dot4+dot1)%10==0)||((dot1+dot5+dot3)%10==0&&(dot4+dot2)%10==0)
				||((dot1+dot4+dot3)%10==0&&(dot2+dot5)%10==0)||((dot1+dot2+dot4)%10==0&&(dot3+dot5)%10==0);
	}
	
	public static boolean isHaveBull(int[] cards) {
		cards=one(cards);
		int dot1=cards[0]%100;
		dot1=ten(dot1);
		int dot2=cards[1]%100;
		dot2=ten(dot2);
		int dot3=cards[2]%100;
		dot3=ten(dot3);
		int dot4=cards[3]%100;
		dot4=ten(dot4);
		int dot5=cards[4]%100;
		dot5=ten(dot5);
		return (dot1+dot2+dot3)%10==0||(dot2+dot3+dot4)%10==0||(dot3+dot4+dot5)%10==0||(dot4+dot5+dot1)%10==0
				||(dot5+dot1+dot2)%10==0||(dot4+dot2+dot5)%10==0||(dot5+dot2+dot3)%10==0||(dot1+dot5+dot3)%10==0
				||(dot1+dot4+dot3)%10==0||(dot1+dot2+dot4)%10==0;
	}
	
	
}
