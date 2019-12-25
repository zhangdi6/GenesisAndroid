package com.iruiyou.pet.activity.cards;


public class CardItem {

    private boolean isAgree;
    private String type;
    private String name;
    private String headIm;
    private int membershipType;
    private int myMemberText;
    private int myMemberText2;
    private int oldPrice;
    private int newPrice;
    private int buttonText;
    private int membershipIcon1;
    private int membershipIcon2;
    private int membershipIcon3;
    private int membershipIcon4;
    private int membershipIcon5;
    private int membershipIcon6;
    private int membershipIcon7;
    private int mTextResource;
    private int mTitleResource;
    private String mTitleEquity1;
    private int mTitleEquity2;
    private int mTitleEquity3;
    private int mTitleEquity5;
    private int mTitleEquity6;
    private int mTitleEquity7;
    private int mTitleEquity8;

    public CardItem(String s, String name, String headIm, int open_membership, int my_senior_member, int my_senior_member1, int i7, int senior_member_price2, int senior_member_card, int i6, int i5, int i4, int i3, int i2, int i1, int i, int senior_member, String chuji2_1, int chuji2_2, int chuji2_3, int chuji2_4, int chuji2_5, int chuji2_6, int title, int text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public CardItem(String type,String name,String headIm,int buttonText,int myMemberText,int myMemberText2,int oldPrice,int newPrice,int membershipType,int membershipIcon1,int membershipIcon2,int membershipIcon3,int membershipIcon4,int membershipIcon5,int membershipIcon6,int membershipIcon7,int mTextResource,String mTitleEquity1, int mTitleEquity2, int mTitleEquity3, int mTitleEquity5, int mTitleEquity6, int mTitleEquity7,int mTitleEquity8) {
        this.type = type;
        this.name = name;
        this.headIm = headIm;
        this.buttonText = buttonText;
        this.myMemberText = myMemberText;
        this.myMemberText2 = myMemberText2;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.membershipType = membershipType;
        this.membershipIcon1 = membershipIcon1;
        this.membershipIcon2 = membershipIcon2;
        this.membershipIcon3 = membershipIcon3;
        this.membershipIcon4 = membershipIcon4;
        this.membershipIcon5 = membershipIcon5;
        this.membershipIcon6 = membershipIcon6;
        this.membershipIcon7 = membershipIcon7;
        this.mTextResource = mTextResource;
        this.mTitleEquity1 = mTitleEquity1;
        this.mTitleEquity2 = mTitleEquity2;
        this.mTitleEquity3 = mTitleEquity3;
        this.mTitleEquity5 = mTitleEquity5;
        this.mTitleEquity6 = mTitleEquity6;
        this.mTitleEquity7 = mTitleEquity7;
        this.mTitleEquity8 = mTitleEquity8;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getButtonText() {
        return buttonText;
    }

    public void setButtonText(int buttonText) {
        this.buttonText = buttonText;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public int getMyMemberText2() {
        return myMemberText2;
    }

    public void setMyMemberText2(int myMemberText2) {
        this.myMemberText2 = myMemberText2;
    }

    public int getMyMemberText() {
        return myMemberText;
    }

    public void setMyMemberText(int myMemberText) {
        this.myMemberText = myMemberText;
    }

    public int getMembershipIcon1() {
        return membershipIcon1;
    }

    public void setMembershipIcon1(int membershipIcon1) {
        this.membershipIcon1 = membershipIcon1;
    }

    public int getMembershipIcon2() {
        return membershipIcon2;
    }

    public void setMembershipIcon2(int membershipIcon2) {
        this.membershipIcon2 = membershipIcon2;
    }

    public int getMembershipIcon3() {
        return membershipIcon3;
    }

    public void setMembershipIcon3(int membershipIcon3) {
        this.membershipIcon3 = membershipIcon3;
    }

    public int getMembershipIcon4() {
        return membershipIcon4;
    }

    public void setMembershipIcon4(int membershipIcon4) {
        this.membershipIcon4 = membershipIcon4;
    }

    public int getMembershipIcon5() {
        return membershipIcon5;
    }

    public void setMembershipIcon5(int membershipIcon5) {
        this.membershipIcon5 = membershipIcon5;
    }

    public int getMembershipIcon6() {
        return membershipIcon6;
    }

    public void setMembershipIcon6(int membershipIcon6) {
        this.membershipIcon6 = membershipIcon6;
    }

    public int getMembershipIcon7() {
        return membershipIcon7;
    }

    public void setMembershipIcon7(int membershipIcon7) {
        this.membershipIcon7 = membershipIcon7;
    }

    public int getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(int membershipType) {
        this.membershipType = membershipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadIm() {
        return headIm;
    }

    public void setHeadIm(String headIm) {
        this.headIm = headIm;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getmTextResource() {
        return mTextResource;
    }

    public void setmTextResource(int mTextResource) {
        this.mTextResource = mTextResource;
    }

    public int getmTitleResource() {
        return mTitleResource;
    }

    public void setmTitleResource(int mTitleResource) {
        this.mTitleResource = mTitleResource;
    }

    public String getmTitleEquity1() {
        return mTitleEquity1;
    }

    public void setmTitleEquity1(String mTitleEquity1) {
        this.mTitleEquity1 = mTitleEquity1;
    }

    public int getmTitleEquity2() {
        return mTitleEquity2;
    }

    public void setmTitleEquity2(int mTitleEquity2) {
        this.mTitleEquity2 = mTitleEquity2;
    }

    public int getmTitleEquity3() {
        return mTitleEquity3;
    }

    public void setmTitleEquity3(int mTitleEquity3) {
        this.mTitleEquity3 = mTitleEquity3;
    }

    public int getmTitleEquity5() {
        return mTitleEquity5;
    }

    public void setmTitleEquity5(int mTitleEquity5) {
        this.mTitleEquity5 = mTitleEquity5;
    }

    public int getmTitleEquity6() {
        return mTitleEquity6;
    }

    public void setmTitleEquity6(int mTitleEquity6) {
        this.mTitleEquity6 = mTitleEquity6;
    }

    public int getmTitleEquity7() {
        return mTitleEquity7;
    }

    public void setmTitleEquity7(int mTitleEquity7) {
        this.mTitleEquity7 = mTitleEquity7;
    }

    public int getmTitleEquity8() {
        return mTitleEquity8;
    }

    public void setmTitleEquity8(int mTitleEquity8) {
        this.mTitleEquity8 = mTitleEquity8;
    }
}
