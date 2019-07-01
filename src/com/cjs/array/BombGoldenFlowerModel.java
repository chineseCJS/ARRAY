package com.cjs.array;

import java.util.Arrays;

public class BombGoldenFlowerModel {
	
	public static void main(String[] args) {
		bombBomb();
	}
	
	public static int bombBomb() {
		int[] porker=porker();
		shuffle(porker);
		int[] cards=subArray(porker, 0, 3);
		String cardsDesc=getCardsDescription(cards);
		System.out.println("手牌:"+cardsDesc);
		int type=cardType(cards);
		String typeDesc=getCardTypeDescription(type);
		System.out.println("牌型:"+typeDesc);
		return type;
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
	
	/**
	 * 判断牌型
	 * 
	 * @param cards 三张牌
	 * @param 牌型    6=炸弹 5=顺金 (同花顺) 4=金花3=顺子 2=对子 1=散牌 0=235
	 */
	public static int cardType(int[] cards) {
		if (isPair(cards)) {
			return 2;
		}
		if (isSameColor(cards)) {
			if (isFlush(cards)) {
				return 5;
			}
			return 4;
		}
		if (isFlush(cards)) {
			return 3;
		}
		if (is235(cards)) {
			return 0;
		}
		if (isBomb(cards)) {
			return 6;
		}
		return 1;
	}
	private static boolean isBomb(int[] cards) {
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		return dot1==dot2&&dot2==dot3;
	}
	private static boolean isSameColor(int[] cards) {
		int color1=cards[0]/100;
		int color2=cards[1]/100;
		int color3=cards[2]/100;
		return color1==color2&&color2==color3;
	}
	private static boolean isFlush(int[] cards) {
		Arrays.sort(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		return dot2-dot1==1&&dot3-dot2==1;
	}
	private static boolean isPair(int[] cards) {
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		if(dot1==dot2&&dot2!=dot3) {
			return true;
		}
		if(dot1==dot3&&dot2!=dot3) {
			return true;
		}
		if(dot2==dot3&&dot2!=dot1) {
			return true;
		}
		return false;
	}
	private static boolean is235(int[] cards) {
		Arrays.sort(cards);
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		
		return dot1==2&&dot2==3&&dot3==5;
	}
	
	/**
	 * 返回牌型描述
	 * @param type
	 * @return
	 */
	private static String getCardTypeDescription(int type) {
		String[] arr= {"235","散牌","对子","顺子","金花","顺金","炸弹"};
		return arr[type];
	}
	
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
}
