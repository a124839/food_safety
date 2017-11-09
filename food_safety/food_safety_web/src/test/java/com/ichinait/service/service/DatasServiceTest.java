package com.ichinait.service.service;

import MSC.MSCMethod;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.mapper.DatasMapper;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.util.JsonMapper;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import scale.Scale;
import smoothpf.Smoothpf;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class DatasServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(DatasServiceTest.class);
	
	@Resource
	private DatasMapper datasMapper;
	
	
	
	public long calute(List<Datas> list){
		ObjectMapper mapper = JsonMapper.nonDefaultMapper().getMapper();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
		
		long start = System.currentTimeMillis();
		for(Datas data:list){
			if(!StringUtils.isEmpty(data.getData())){
				try {
					List<String> readValue = mapper.readValue(data.getData(), type);

					for(String str:readValue){
						String[] ary = str.split(",");

					}
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	
	@Test
	public void avg(){
//		List<Datas> list = datasMapper.selectByExampleWithBLOBs(new DatasExample());
		int n = 3;
		long total = 0;
//		for(int i = 0;i<n;i++){
//			total += calute(list);
//		}
		System.out.println(total/n);
	}
	
	@Test
	public void queryData(){
        try {
            double[] ary = getDatas();

//                MWNumericArray x = new MWNumericArray(getX(strs).toArray(), MWClassID.DOUBLE);
//                MWNumericArray y = new MWNumericArray(getY(strs).toArray(),MWClassID.DOUBLE);
            MWNumericArray x = new MWNumericArray(ary,MWClassID.DOUBLE);

            MSCMethod mscMethod = new MSCMethod();
            Object[] obj = mscMethod.MSC(2,x,1,1);
            MWNumericArray temp = (MWNumericArray)obj[0];
            MWNumericArray temp1 = (MWNumericArray)obj[1];
            double[][] d = (double[][]) temp.toDoubleArray();
            double[][] d1 = (double[][]) temp1.toDoubleArray();
//            for(double[] dD:d){
//                double[] ary1 = new double[2];
//                for(int i = 0;i<dD.length;i++){
//                    BigDecimal b   =   new   BigDecimal(dD[i]);
//                    double vv = b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
//                    ary1[i] = vv;
//                }
//                System.out.println("=================================="+Arrays.toString(ary1));
//            }
			out(d);
           out(d1);
		} catch (MWException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toJson(){
		ValuesDTO d = new ValuesDTO();
		d.setX(0);
		JsonMapper mapper = new JsonMapper();
		System.out.println(mapper.toJson(d));
	}


//	public double[][] getDoubleAry(List<ValuesDTO> dtos){
//		double[][] arys = new double[dtos.size()][2];
//		List<double[]> list = Lists.newArrayList();
//		for(int i = 0;i<dtos.size();i++){
//            ValuesDTO dto = dtos.get(i);
//			double x = dto.getX();
//			double y = dto.getY();
////			double[] ary = {x,y};
//			arys[i][0] = x;
//            arys[i][1]=y;
//		}
//		return arys;
//	}

	public double[] getDoubleAry(List<ValuesDTO> dtos){
		double[] arys = new double[dtos.size()];
		List<double[]> list = Lists.newArrayList();
		for(int i = 0;i<dtos.size();i++){
			ValuesDTO dto = dtos.get(i);
			double y = dto.getY();
			arys[i]=y;
		}
		return arys;
	}

    public List<Double> getX(List<ValuesDTO> dtos){
        List<Double> list = Lists.newArrayList();
        for(ValuesDTO dto:dtos){
            list.add(dto.getX());
        }
        return list;
    }

    public List<Double> getY(List<ValuesDTO> dtos){
        List<Double> list = Lists.newArrayList();
        for(ValuesDTO dto:dtos){
            list.add(dto.getY());
        }
        return list;
    }

    @Test
    public void testAlgorithm(){
        double[][] ary = {{0.0258,0.0208},{0.0258,0.0148}};


        MWNumericArray x = new MWNumericArray(ary,MWClassID.DOUBLE);
        try {
            MSCMethod mscMethod = new MSCMethod();
			long end = 0;
            long start = System.currentTimeMillis();
            for(int i = 0;i<10000;i++){
                Object[] obj = mscMethod.MSC(2,x,1,1);
                if((i%100) == 0){
                    end = System.currentTimeMillis();
					System.out.println("==============  "+(end - start));
                    start = end;
				}
//				MWNumericArray temp = (MWNumericArray)obj[0];
//				MWNumericArray temp1 = (MWNumericArray)obj[1];
//				double[][] d = (double[][]) temp.toDoubleArray();
//				for(double[] dD:d){
//					System.out.println(Arrays.toString(dD));
//				}
//				System.out.println("===========================================");
//
//				double[][] d1 = (double[][]) temp1.toDoubleArray();
//				for(double[] dD:d1){
//					System.out.println(Arrays.toString(dD));
//				}
			}
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

	@Test
	public void scaleTest() {
		double[] d = getDatas();
        try {
			MWNumericArray x = new MWNumericArray(d,MWClassID.DOUBLE);
			Scale scale = new Scale();
			Object[] obj = scale.scale(2,d,1.0);
			MWNumericArray temp = (MWNumericArray)obj[0];
			MWNumericArray temp1 = (MWNumericArray)obj[1];
			out((double[][]) temp.toDoubleArray());
			out((double[][]) temp1.toDoubleArray());

		} catch (MWException e) {
			e.printStackTrace();
		}
	}


    @Test
    public void smoothTest() {
        double[] d = getDatas();
		File to = new File("d:"+File.separator+"out.txt");
		FileWriter writer = null;
        try {
            MWNumericArray x = new MWNumericArray(d,MWClassID.DOUBLE);
            Smoothpf smoothpf = new Smoothpf();
            Object[] obj = smoothpf.smoothpf(1,x,1,2,3);
            MWNumericArray temp = (MWNumericArray)obj[0];
//            MWNumericArray temp1 = (MWNumericArray)obj[1];
            double[][] dd = (double[][]) temp.toDoubleArray();
			writer = new FileWriter(to,true);
            for(double[] d1:dd){
//				writer.write(Arrays.toString(d1));
//				Files.write((Arrays.toString(d1)+"\t\n").getBytes(),to);
				System.out.println(Arrays.toString(d1));
            }
//            System.out.println(temp1.getClass().getTypeName());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public double[] getDatas(){
		String id = "02e84789-bfc2-4ce1-b3e2-3872e671347f";
		Datas datas = datasMapper.selectByPrimaryKey(id);
		String data = datas.getData();
		JsonMapper mapper = new JsonMapper();
		JavaType type = mapper.constructCollectionType(List.class, ValuesDTO.class);
		List<ValuesDTO>  strs = mapper.fromJson(data, type);
		double[]ary = getDoubleAry(strs);
		return ary;
	}

	private void out(double[][] dd){
		for(double[] d1:dd){
				System.out.println("============================"+Arrays.toString(d1));
		}
	}

	@Test
	public  void StringConverter(){
		String name = "abc_bcd_001";
		System.out.println(name.substring(0,name.lastIndexOf("_")));
		String str = "中国";
		try {
			System.out.println(str.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}



}
