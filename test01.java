package sec01;

import java.util.Scanner;

public class test01 {

	static int hero_Level, hero_power, hero_defense, hero_hp, hero_mp, hero_experience, hero_money;
	static int monster_hp, monster_defense, monster_power, monster_mp, monster_Level, monster_experience, monster_money;
	static String hero_name, monster_name;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// 영웅 정보 입력
		System.out.print("영웅의 이름을 입력하세요 : ");
		hero_name = in.next();

		// 기본값 설정
		hero_Level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_mp = 0;
		hero_experience = 0;
		hero_money = 0;

		// 영웅 정보 출력
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		System.out.println("********************");
		System.out.println("현재 Hero 의 이름 : " + hero_name);
		System.out.println("현재 " + hero_name + "의 레벨 : " + hero_Level);
		System.out.println("현재 " + hero_name + "의 힘 : " + hero_power);
		System.out.println("현재 " + hero_name + "의 방어력 : " + hero_defense);
		System.out.println("현재 " + hero_name + "의 HP : " + hero_hp);
		System.out.println("현재 " + hero_name + "의 MP : " + hero_mp);
		System.out.println("현재 " + hero_name + "의 경험치 : " + hero_experience);
		System.out.println("현재 " + hero_name + "의 돈 : " + hero_money + "원");

		while (true) {
			// 장소 선택
			System.out.println("********************");
			System.out.println("1. 사냥터");
			System.out.println("2. 포션 상점");

			int place_choice;
			while (true) {
				System.out.print("입장할 장소를 입력하세요. : ");
				place_choice = in.nextInt();

				if (place_choice == 1) { // 1번 선택 시 사냥터 입장
					System.out.println("사냥터에 입장하였습니다.");
					break;
				} else if (place_choice == 2) { // 2번 선택 시 포션 상점 입장
					System.out.println("포션 상점에 입장하였습니다.");
					System.out.println("1. 힘 증감 포션 (30원)");
					System.out.println("2. 방어력 증감 포션 (30원)");
					System.out.println("3. 경험치 증감 포션 (100원)");
					System.out.println("4. HP 증감 포션 (10원)");
					System.out.println("5. MP 증감 포션 (10원)");

					System.out.print("원하시는 물건을 입력하세요 : ");
					int num = in.nextInt(); // 물건 선택
					potionStore_show(num);

				} else {
					System.out.println("잘못된 번호를 선택하셨습니다.");
				}
			}

			int monster_choice;
			while (true) {
				// 몬스터 선택
				System.out.println("1. 너구리");
				System.out.println("2. 살쾡이");
				do {
					System.out.print("전투할 상대를 입력하세요. : ");
					monster_choice = in.nextInt();

				} while (monster_choice != 1 && monster_choice != 2);
				// 너구리 기본값 설정
				if (monster_choice == 1) {
					monster_name = "너구리";
					monster_hp = 100;
					monster_mp = 0;
					monster_Level = 1;
					monster_power = 20;
					monster_defense = 5;
					monster_money = 10;
					monster_experience = 10;
				} else {
					// 살쾡이 기본값 설정
					monster_name = "살쾡이";
					monster_hp = 2000;
					monster_mp = 0;
					monster_Level = 5;
					monster_power = 100;
					monster_defense = 20;
					monster_money = 30;
					monster_experience = 50;
				}
				System.out.println(monster_name + "와 전투를 시작합니다.");

				int attack_num;
				while (true) {
					// 히어로 공격
					System.out.println("1. 쓰러스트"); // 공격 번호 선택
					System.out.print("공격 번호를 입력하세요 : ");
					attack_num = in.nextInt();

					int monster_damage = hero_attack();
					hero_attacked(monster_damage);

					// 몬스터 죽으면 반복 종료
					if (monster_hp <= 0) {
						hero_Level_up(); // 히어로 레벨업
						break;

					}
					// 몬스터 공격
					int hero_damage = monster_attack();
					monster_attacked(hero_damage);

					// 히어로 죽으면 반복 종료
					if (hero_hp == 1) {
						break;

					}
				}
				break;
			}
		}
	}

	// 포션 상점 입장
	static void potionStore_show(int num) {
		System.out.println("********************");
		boolean ok = false; // 구매 성공 여부 확인

		// 포션 구매
		switch (num) {
		case 1 -> {
			if (hero_money >= 30) {
				hero_power += 3;
				hero_money -= 30;
				System.out.println("힘 증감 포션 구입이 완료되었습니다.");
				ok = true;
			}
		}
		case 2 -> {
			if (hero_money >= 30) {
				hero_defense += 3;
				hero_money -= 30;
				System.out.println("방어력 증감 포션 구입이 완료되었습니다.");
				ok = true;
			}
		}
		case 3 -> {
			if (hero_money >= 100) {
				hero_experience += 50;
				hero_money -= 100;
				System.out.println("경험치 증감 포션 구입이 완료되었습니다.");
				ok = true;
			}
		}
		case 4 -> {
			if (hero_money >= 10) {
				hero_hp += 50;
				hero_money -= 10;
				System.out.println("HP 증감 포션 구입이 완료되었습니다.");
				ok = true;
			}
		}
		default -> {
			if (hero_money >= 10) {
				hero_mp += 50;
				hero_money -= 10;
				System.out.println("MP 증감 포션 구입이 완료되었습니다.");
				ok = true;
			}
		}

		}
		// 포션 구매 후 남은 금액 출력
		if (ok) {
			System.out.println("포션 상점에서 물건을 구매 시도하는 중입니다.");
			System.out.println("구입이 완료되었습니다.");
			System.out.println(hero_money + "원 남았습니다.");

			System.out.println("********************");
			System.out.println("현재 Hero 의 이름 : " + hero_name);
			System.out.println("현재 " + hero_name + "의 레벨 : " + hero_Level);
			System.out.println("현재 " + hero_name + "의 힘 : " + hero_power);
			System.out.println("현재 " + hero_name + "의 방어력 : " + hero_defense);
			System.out.println("현재 " + hero_name + "의 HP : " + hero_hp);
			System.out.println("현재 " + hero_name + "의 MP : " + hero_mp);
			System.out.println("현재 " + hero_name + "의 경험치 : " + hero_experience);
			System.out.println("현재 " + hero_name + "의 돈 : " + hero_money + "원");
			System.out.println("********************");

		} else {
			// 금액 부족할 경우 구매 불가 문구 출력
			System.out.println("돈이 부족하여 구매 실패하였습니다.");
		}

	}

	static int hero_attack() {
		// 히어로가 공격할 경우
		int sum = 0; // 데미지 초기화
		// 몬스터 데미지 계산
		sum = hero_Level * 10 + hero_power * 30;
		System.out.println(hero_name + "의 공격입니다.");
		System.out.println(monster_name + " 데미지는 " + sum + "입니다.");
		return sum;
	}

	static void hero_attacked(int sum) {
		// 몬스터가 방어할 경우
		if (monster_defense >= sum) {
			System.out.println(monster_name + "가 공격을 막아냈습니다. 몬스터 체력이 그대로 유지됩니다.");
		} else {
			monster_hp = monster_hp + monster_defense - sum;
		}

		// 몬스터 죽음
		while (monster_hp <= 0) {
			System.out.println(monster_name + "가 죽었습니다!");
			hero_experience += monster_experience; // 몬스터 경험치만큼 히어로 경험치 증가
			hero_money += monster_money; // 몬스터 돈만큼 히어로 돈 증가

			// 히어로 경험치와 돈 증가 출력
			System.out.println("********************");
			System.out.println("현재 Hero 의 이름 : " + hero_name);
			System.out.println("현재 " + hero_name + "의 레벨 : " + hero_Level);
			System.out.println("현재 " + hero_name + "의 힘 : " + hero_power);
			System.out.println("현재 " + hero_name + "의 방어력 : " + hero_defense);
			System.out.println("현재 " + hero_name + "의 HP : " + hero_hp);
			System.out.println("현재 " + hero_name + "의 경험치 : " + hero_experience);
			System.out.println("현재 " + hero_name + "의 돈 : " + hero_money + "원");

			break;
		}

	}

	static int monster_attack() {
		// 몬스터가 공격할 경우
		int sum = 0; // 데미지 초기화
		// 몬스터 데미지 계산
		sum = monster_power;
		System.out.println(monster_name + "의 공격입니다.");
		System.out.println(hero_name + " 데미지는 " + sum + "입니다.");
		return sum;
	}

	static void monster_attacked(int sum) {
		// 히어로가 방어할 경우
		if (hero_defense >= sum) {
			System.out.println(hero_name + "가 공격을 막아냈습니다. 히어로 체력이 그대로 유지됩니다.");
		} else {
			hero_hp = hero_hp + hero_defense - monster_power;
		}

		// 히어로 죽음
		while (hero_hp <= 0) {
			System.out.println(hero_name + "가 죽었습니다.");
			hero_hp = 1; // 히어로 hp.1 로 부활
			System.out.println("hp.01로 부활합니다!");

		}

	}

	static void hero_Level_up() {
		while (hero_experience >= hero_Level * 80) {
			hero_Level++; // 히어로 레벨업
			System.out.println(hero_name + "의 레벨이 " + hero_Level + "이 되었습니다.");

			// 레벨업 기념 돈 증가
			hero_money += hero_Level * 50;
			System.out.println("레벨업 기념으로 돈이 " + (hero_Level * 50) + "원 증가하여 ");
			System.out.println(hero_money + "원이 되었습니다.");

			// 히어로 경험치 초기화
			hero_experience = 0;

			// 출력
			System.out.println("********************");
			System.out.println("현재 Hero 의 이름 : " + hero_name);
			System.out.println("현재 " + hero_name + "의 레벨 : " + hero_Level);
			System.out.println("현재 " + hero_name + "의 힘 : " + hero_power);
			System.out.println("현재 " + hero_name + "의 방어력 : " + hero_defense);
			System.out.println("현재 " + hero_name + "의 HP : " + hero_hp);
			System.out.println("현재 " + hero_name + "의 경험치 : " + hero_experience);
			System.out.println("현재 " + hero_name + "의 돈 : " + hero_money + "원");

		}
	}

}
