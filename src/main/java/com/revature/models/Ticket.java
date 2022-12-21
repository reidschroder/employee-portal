package com.revature.models;

public class Ticket {

    private int ers_reimbursement_id;
    private int reimbursement_amount;
    private String reimbursement_description;
    private int user_id_author_fk;
    private int user_id_resolver_fk;
    private int reimbursement_type_id_fk;
    private int reimbursement_status_id_fk;


    //CONSTRUCTORS ==========================================


    public Ticket() {
    }

    public Ticket(int ers_reimbursement_id, int reimbursement_amount, String reimbursement_description) {
        this.ers_reimbursement_id = ers_reimbursement_id;
        this.reimbursement_amount = reimbursement_amount;
        this.reimbursement_description = reimbursement_description;
    }

    public Ticket(int ers_reimbursement_id, int reimbursement_amount, String reimbursement_description, int user_id_author_fk, int user_id_resolver_fk, int reimbursement_type_id_fk, int reimbursement_status_id_fk) {
        this.ers_reimbursement_id = ers_reimbursement_id;
        this.reimbursement_amount = reimbursement_amount;
        this.reimbursement_description = reimbursement_description;
        this.user_id_author_fk = user_id_author_fk;
        this.user_id_resolver_fk = user_id_resolver_fk;
        this.reimbursement_type_id_fk = reimbursement_type_id_fk;
        this.reimbursement_status_id_fk = reimbursement_status_id_fk;
    }

    public Ticket(int ers_reimbursement_id, int reimbursement_amount, String reimbursement_description, int reimbursement_status_id_fk) {
        this.ers_reimbursement_id = ers_reimbursement_id;
        this.reimbursement_amount = reimbursement_amount;
        this.reimbursement_description = reimbursement_description;
        this.reimbursement_status_id_fk = reimbursement_status_id_fk;
    }

    //GETTERS & SETTERS ====================================


    public int getErs_reimbursement_id() {
        return ers_reimbursement_id;
    }

    public void setErs_reimbursement_id(int ers_reimbursement_id) {
        this.ers_reimbursement_id = ers_reimbursement_id;
    }

    public int getReimbursement_amount() {
        return reimbursement_amount;
    }

    public void setReimbursement_amount(int reimbursement_amount) {
        this.reimbursement_amount = reimbursement_amount;
    }

    public String getReimbursement_description() {
        return reimbursement_description;
    }

    public void setReimbursement_description(String reimbursement_description) {
        this.reimbursement_description = reimbursement_description;
    }

    public int getUser_id_author_fk() {
        return user_id_author_fk;
    }

    public void setUser_id_author_fk(int user_id_author_fk) {
        this.user_id_author_fk = user_id_author_fk;
    }

    public int getUser_id_resolver_fk() {
        return user_id_resolver_fk;
    }

    public void setUser_id_resolver_fk(int user_id_resolver_fk) {
        this.user_id_resolver_fk = user_id_resolver_fk;
    }

    public int getReimbursement_type_id_fk() {
        return reimbursement_type_id_fk;
    }

    public void setReimbursement_type_id_fk(int reimbursement_type_id_fk) {
        this.reimbursement_type_id_fk = reimbursement_type_id_fk;
    }

    public int getReimbursement_status_id_fk() {
        return reimbursement_status_id_fk;
    }

    public void setReimbursement_status_id_fk(int reimbursement_status_id_fk) {
        this.reimbursement_status_id_fk = reimbursement_status_id_fk;
    }

    @Override
    public String toString() {
        return "Ticket{" + ers_reimbursement_id +
                ", reimbursement_amount='" + reimbursement_amount + '\'' +
                ", reimbursement_description='" + reimbursement_description + '\'' +
                '}';
    }
}
