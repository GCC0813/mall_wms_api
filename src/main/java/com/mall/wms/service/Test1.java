package com.mall.wms.service;

import java.util.*;

/**
 * @author GCC
 * @date 2020/7/10 17:30
 */
public class Test1 {

    public static void main(String[] args) {
     /*   //汉字转换成ASCII码
        String str = "最近太他妈烦了谁能陪我说说话呀";

        String str1 = "最近好他妈烦了谁能陪我说说话啦";

        char[] a = str.toCharArray();
        char[] b= str1.toCharArray();
        int aa = 0;
        for (char value : a) {
            for (char value1 : b) {
                if(value==value1){
                    aa++;
                }
            }
        }

        System.out.println(aa);*/

     /*   Set<Integer> a = Sets.newHashSet();
        a.add(1);
        a.add(2);
        a.add(3);
        Set<Integer> b = Sets.newHashSet();
        b.add(4);
        b.add(2);
        b.add(7);
        Set<Integer> c = Sets.newHashSet();

        for(Integer i :a){
            if(b.contains(i)){
                c.add(i);
            }
        }
        System.out.println(c);*/
        /*String version = "14.0001";
        System.out.println(version.compareTo("14.0"));;*/
     /*   System.out.println(Math.round(3750/100));
        System.out.println(Math.round(96.499999999));
        System.out.println(Math.round(96.9));
        double a = 3750;
        System.out.println(Math.round(a/100));*/

        /*Integer b = 3750;
        double c = b;
        Integer d =(int) Math.round(c/100);
        System.out.println(d);*/
       /* try {
            System.out.println("aaaaaa");
            int a = 10/0;
        }catch (Exception e){
            System.out.println("832");
        }
        System.out.println("11111");*/

       /* Set<Integer> fullAttendanceAwardIds = Sets.newHashSetWithExpectedSize(3);
        fullAttendanceAwardIds.add(1);
        fullAttendanceAwardIds.add(2);
        fullAttendanceAwardIds.add(3);
        System.out.println(fullAttendanceAwardIds);
        List<Integer> a = new ArrayList<>(fullAttendanceAwardIds);
        System.out.println(a);*/

       /* String yearMon = "202012";
        Integer lastMon = Integer.parseInt(yearMon.substring(yearMon.length()-2));
        System.out.println(lastMon);*/

        String str1 = "张三,李四,王五,陈六,吕七,孙八,赵九";
        String str2 = "王五,孙八";

        String[] arr1 = str1.split(",");
        String[] arr2 = str2.split(",");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j].equals(arr2[i])) {
                    arr1[j] = "";
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < arr1.length; j++) {
            if (!"".equals(arr1[j])) {
                sb.append(arr1[j] + ",");
            }
        }
        System.out.println("结果："+ sb.toString().substring(0, sb.toString().length() - 1));


    }


    private String CompareData(String before, String latest){// 如果两个字符串相同，那么就不需要进行对比了
        if (before.equals(latest)) {return "";}// 执行分割
        String[] s1 = before.split("@");
        String[] s2 = latest.split("@");// 定义返回比对信息
        StringBuilder contrastData = new StringBuilder();// 将信息保存到哈希表中
        Map<String, String> pack_before = new HashMap<String, String>();
        Map<String, String> pack_latest = new HashMap<String, String>();// 保存上一次信息过程
        for (String temp: s1) {
            if (temp.length() == 0) { continue; }
            pack_before.put(temp.split("#")[0], temp.split("#")[1]);
        }// 保存当前信息过程
        for (String temp: s2) {
            if (temp.length() == 0) { continue; }
            pack_latest.put(temp.split("#")[0], temp.split("#")[1]);
        }// 遍历前一次信息，与当前信息做以对比
        for (Map.Entry e: pack_before.entrySet()) {// 如果存在信息，进行下一步操作，否则，前一次信息中在当前执行的情况下，存在被删除的情况
            if(pack_latest.containsKey(e.getKey())) {// 对比前一次与当前的结果值是否相同，不同的话进一步对比
                if (! pack_latest.get(e.getKey()).equals(e.getValue())) {// 将包信息保存到整体信息中
                    contrastData.append("P:" + e.getKey() + "\n");// 将类信息保存到链表中
                    List<String> beforeList = new ArrayList<>();
                    List<String> latestList = new ArrayList<>();// 获取类信息的数组
                    String[] before_str = e.getValue().toString().split("\\|");
                    String[] latest_str = pack_latest.get(e.getKey()).toString().split("\\|");
// 遍历保存
                    for (String temp: before_str){
                        beforeList.add(temp);
                    }
                    for (String temp: latest_str){
                        latestList.add(temp);
                    }
                    // 将后一次中存在前一次的所有元素删除
                    latestList.removeAll(beforeList);

                    for (String latestTemp : latestList) {
                        String getClass = latestTemp.split("=")[0];// 决定是否存在对比的信息
                        boolean flag = true;
                        for (String beforeTemp: beforeList) {
                            if (beforeTemp.split("=")[0].equals(getClass)) {
                                flag = false;
                            }
                            if (contrastData.toString().contains(latestTemp)) {continue;}
                            if (flag) {// 新增加的信息
                                contrastData.append("NC:" + latestTemp + "\n");
                            } else {// 可以对比的信息，B代表前一次的，L代表最新一次
                                contrastData.append("B:" + beforeTemp + "-L:" + latestTemp + "\n");
                                flag = true;
                            }
                        }
                    }
                }
            } else {
                System.out.println("already deleted!!" + e.getKey());
            }
        }
        return contrastData.toString();
    }
}
