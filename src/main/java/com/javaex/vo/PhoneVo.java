package com.javaex.vo;

public class PhoneVo {
		
		//필드
		public String name, hp, company; 
		public int personId ;
		
		//생성자
		public PhoneVo (){}
		
		public PhoneVo(int personId, String name, String hp, String company){
			this.name = name;
			this.hp = hp;
			this.company = company;
			this.personId = personId;
		}

		public PhoneVo(String name, String hp, String company) {
			
			this.name = name;
			this.hp = hp;
			this.company = company;
		}

		@Override
		public String toString() {
			return "PhoneVo [name=" + name + ", hp=" + hp + ", company=" + company + ", personId=" + personId + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHp() {
			return hp;
		}

		public void setHp(String hp) {
			this.hp = hp;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public int getPersonId() {
			return personId;
		}

		public void setPersonId(int personId) {
			this.personId = personId;
		}
		
		
		
}
