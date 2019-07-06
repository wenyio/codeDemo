package com.iscolt.weatherforecast.web.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.iscolt.weatherforecast.model.dto.JsonResult;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONException;
import org.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 天气控制器
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/6/20
 * @Description:com.iscolt.weather.forecast.rest.template.web.controller
 * @version:1.0
 */
@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    private HttpServletRequest request;

    private final String MY_SENIVERSE_KEY = "SBPjmOpYIIuN8163-";

    private final String MY_BMAP_AK = "8GpQf7gqQ8PCiOP5BsYFdTQ0HGbbpc9x";

    /**
     * 根据城市名称查数据
     *
     * @param cityName
     * @return
     */
    @GetMapping("/cityName/{cityName}")
    public Object getReportByCityName(@PathVariable("cityName") String cityName) {
        String uri = null;
        Object strBody = null;
        ResponseEntity<String> response = null;

        // 如果城市名称不为空: 发送请求
        if (null != getWeatherApiByCityName(cityName)) {
            uri = getWeatherApiByCityName(cityName);
            response = restTemplate.getForEntity(uri, String.class);
        } else {
            return new JsonResult(HttpStatus.NOT_FOUND.value(), "没有查到城市");
        }

        // 如果请求成功: 获取值
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        } else {
            return new JsonResult(HttpStatus.NO_CONTENT.value(), "服务器内部错误");
        }

        return strBody;
    }

    /**
     * 自动获取地址查数据
     *
     * @return
     */
    @GetMapping("/cityName")
    public Object getReportByCityName() {
        String uri = null;
        String city = null;
        Object strBody = null;
        ResponseEntity<String> response = null;

        city = getCity();
        if (null != city) {
            // 如果城市名称不为空: 发送请求
            if (null != getWeatherApiByCityName(getCity())) {
                uri = getWeatherApiByCityName(getCity());
                response = restTemplate.getForEntity(uri, String.class);
            } else {
                return new JsonResult(HttpStatus.NOT_FOUND.value(), "请求地址错误");
            }
        } else {
            return new JsonResult(HttpStatus.NOT_FOUND.value(), "自动获取城市失败");
        }

        // 如果请求成功: 获取值
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        } else {
            return new JsonResult(HttpStatus.NO_CONTENT.value(), "服务器内部错误");
        }

        return strBody;
    }

    /**
     * 传入城市名 获取api 默认中国
     *
     * @param cityName
     * @return
     */
    public final String getWeatherApiByCityName(String cityName) {
        return "https://api.seniverse.com/v3/weather/now.json?key="+MY_SENIVERSE_KEY+"&location="+cityName+"&language=zh-Hans&unit=c";
    }

    /**
     * 处理数据
     *
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 处理数据
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    /**
     * 获取当前城市
     *
     * @return
     */
    public final String getCity() {
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        JSONObject json = null;
        try {
            // Jsoup 获取ip
            json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak="+MY_BMAP_AK+"&ip="+getPublicIP());
            // hutool 工具获取ip TODO request 似乎没有被注入
            //json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak="+MY_BMAP_AK+"&ip="+ ServletUtil.getClientIP(request));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject cityInfo = null;
        try {
            cityInfo = (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String city = null;
        try {
            city = (String) cityInfo.get("city");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * 获取当前网络ip
     *
     * @return
     */
    public static String getPublicIP() {
        String ip = "";
        try {
            Document doc = Jsoup.connect("http://www.ip138.com/ip2city.asp").ignoreContentType(false).get();
            Elements els = doc.select("center");
            for (org.jsoup.nodes.Element el : els) {
                ip = el.text();
            }
            ip = ip.replaceAll("[^0-9.]", "");
        } catch (IOException e1) {
            // e1.printStackTrace();
            // System.out.println("问题不大");
        }finally {

        }
        return ip;
    }
}
