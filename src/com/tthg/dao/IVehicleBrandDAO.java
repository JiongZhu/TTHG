package com.tthg.dao;
import java.util.List;
import com.tthg.entity.VehicleBrand;
public interface IVehicleBrandDAO {
	void add(VehicleBrand vehiclebrand);
	void delete(int[] ids);
	void update(VehicleBrand vehiclebrand);
	List<VehicleBrand> searchAll();
	List<VehicleBrand> searchVehicleBrand(VehicleBrand vehiclebrand);
}
