package com.Module.BlockList;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockListTest {

	public static void main(String[] args) throws Exception {
		// getAdd();
		// getUpdate();
		// getDelete();
		// getFindByPk();
		getSearch();
	}

	static void getAdd() throws Exception {
		BlockListBean bean = new BlockListBean();
		BlockListModel m = new BlockListModel();

		bean.setCode("BLK011");
		bean.setName("User K");
		bean.setReason("Spam");
		bean.setStatus("Active");
		long l = m.add(bean);
		System.out.println(l + " row Added in table");
	}

	static void getUpdate() throws Exception {
		BlockListBean bean = new BlockListBean();
		BlockListModel m = new BlockListModel();
		bean.setId(11);
		bean.setCode("BLK011");
		bean.setName("User Z");
		bean.setReason("Non-Spam");
		bean.setStatus("Active");
		m.update(bean);
	}

	static void getDelete() throws Exception {
		BlockListBean bean = new BlockListBean();
		BlockListModel m = new BlockListModel();
		bean.setId(11);

		m.delete(bean);
	}

	static void getFindByPk() throws Exception {
		BlockListBean bean = new BlockListBean();
		BlockListModel m = new BlockListModel();

		bean = m.FindByPk(4);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getReason());
			System.out.println(bean.getStatus());
			System.out.println("-----------------");
		} else {
			System.out.println("No Record found");
		}

	}

	public static void getSearch() throws Exception {
		BlockListBean bean = new BlockListBean();
		BlockListModel m = new BlockListModel();
		List<BlockListBean> list = new ArrayList<BlockListBean>();
		
	//	bean.setName("U");
	//	bean.setReason("Fraud");
	//	bean.setStatus("Active");
		bean.setCode("BLK003");
		
		list = m.search(bean, 1, 5);
		Iterator<BlockListBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getReason());
			System.out.println(bean.getStatus());
			System.out.println("-----------------");
		}
	}

}
