package n3.home.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import n3.home.vo.TestVO;

import org.dozer.DozerBeanMapper;

public class DozerServlet extends HttpServlet {

	private static final long serialVersionUID = -7585207762978285771L;
	private DozerBeanMapper dozer;
	
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		createDozer();
		dozerTest();
	}

	private void dozerTest() {
//		TestEntity entity = new TestEntity();
		TestVO vo = new TestVO();
		vo.setName("yemao");
		vo.setPassword("123");
		vo.setPasswordCif("1234");
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "sdfw");
		map.put("password", "122343");
		dozer.map(vo, map);
		System.out.println(map.get("id"));
		System.out.println(map.get("name"));
		System.out.println(map.get("password"));
		System.out.println(map.get("passwordCif"));
	}

	private void createDozer() {
		if(dozer==null) {
			dozer = new DozerBeanMapper();
			List<String> mappings = new ArrayList<String>();
			mappings.add("dozer-mappings.xml");
			dozer.setMappingFiles(mappings);
		}
	}
}
