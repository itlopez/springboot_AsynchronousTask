package com.example;

import com.example.dto.SchoolDto;
import com.example.entity.School;
import com.example.resultmapper.SchoolRowMapper;
import com.example.service.MsgFutureServer;
import com.example.service.MsgServer;
import com.example.service.SchoolService;
import com.example.service.impl.SchoolServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAsyncApplicationTests {

	@Autowired
	private MsgServer msgServer;

	@Autowired
	private MsgFutureServer msgFutureServer;

	@Autowired
	private SchoolService schoolService;

	@Resource(name = "oneJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Resource(name = "twoJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Test
	public void contextLoads() throws Exception{
		msgServer.sendA();
		msgServer.sendB();
	}

	@Test
	public void test() throws Exception {
		long startTime = System.currentTimeMillis();

		Future<String> task1 = msgFutureServer.sendA();
		Future<String> task2 = msgFutureServer.sendB();
		while(true) {
			if(task1.isDone() && task2.isDone() ) {
				break;
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("总耗时：" + (endTime - startTime));
		System.out.println("a与b执行完成后准备执行c");
	}

	@Test
	public void queryListSchoolByIndex(){
		Long time = System.currentTimeMillis();
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setFromIndex(0);
		schoolDto.setToIndex(200000);
		List<School> schoolList = schoolService.queryListSchoolByIndex(schoolDto);
		System.out.println("同步查询速度为：" + (System.currentTimeMillis() - time));//11002ms
		System.out.println(schoolList.size());
	}

	//直接丢给后端去处理即可。不管成功与否
	@Test
	public void queryAsync(){//7
		Long time = System.currentTimeMillis();
		SchoolDto AschoolDto = new SchoolDto();
		AschoolDto.setFromIndex(0);
		AschoolDto.setToIndex(100000);
		List<School> ASchoolList = schoolService.quesryA(AschoolDto);
		System.out.println("A正在查询");
		SchoolDto BschoolDto = new SchoolDto();
		BschoolDto.setFromIndex(100001);
		BschoolDto.setToIndex(200000);
		List<School> BSchoolList = schoolService.quesryB(BschoolDto);
		System.out.println("B正在查询");
		System.out.println("异步查询速度为：" + (System.currentTimeMillis() - time));
	}

	/**
	 * A 和B任务必须完成后执行下面的 "异步查询速度为:" 代码
	 */
	@Test
	public void queryAsyncFuture(){//29188ms
		Long time = System.currentTimeMillis();
		SchoolDto AschoolDto = new SchoolDto();
		AschoolDto.setFromIndex(0);
		AschoolDto.setToIndex(100000);
		Future<String> task1 = schoolService.sendA(AschoolDto);
		System.out.println("A正在查询");
		SchoolDto BschoolDto = new SchoolDto();
		BschoolDto.setFromIndex(100001);
		BschoolDto.setToIndex(200000);
		System.out.println("B正在查询");
		Future<String> task2 =schoolService.sendB(BschoolDto);
		while(true) {
			if(task1.isDone() && task2.isDone() ) {
				break;
			}
		}
		System.out.println("异步查询速度为：" + (System.currentTimeMillis() - time));
	}

	@Test
	public void testTwoDataSource(){
		String queryStr = "SELECT *  FROM school limit 1";
		School school = jdbcTemplate1.queryForObject(queryStr,new SchoolRowMapper());
		System.out.println(school.getName());
		String sql = "INSERT INTO `school` VALUES ('" + school.getId()  +"', '" + school.getName() + "', '" + school.getOrgNo() + "', '0', '1', '1')";

		jdbcTemplate2.execute(sql);
	}

}
