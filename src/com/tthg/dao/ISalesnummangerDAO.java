package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Salesnum;

public interface ISalesnummangerDAO {
void add(Salesnum sanum);
void delete(int[] ids);
void update(Salesnum sanum);
List<Salesnum> searchAll();
List<Salesnum> searchSalesnum(Salesnum salesnum);
}
