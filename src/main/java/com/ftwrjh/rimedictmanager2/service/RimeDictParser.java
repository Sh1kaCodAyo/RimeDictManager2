//package com.ftwrjh.rimedictmanager2.service;
//
//import com.ftwrjh.rimedictmanager2.data.constant.DictionaryType;
//import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
//import org.yaml.snakeyaml.Yaml;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.*;
//
//public class RimeDictParser {
//
//    public RimeDictData parse(Path filePath) throws IOException {
//        List<String> lines = Files.readAllLines(filePath, java.nio.charset.StandardCharsets.UTF_8);
//
//        // 1. 找到 YAML 部分的开始和结束
//        int startIndex = -1;  // "---" 的位置
//        int endIndex = -1;    // "..." 的位置
//
//        for (int i = 0; i < lines.size(); i++) {
//            String line = lines.get(i).trim();
//            if (line.equals("---") && startIndex == -1) {
//                startIndex = i;
//            }
//            if (line.equals("...") && startIndex != -1 && endIndex == -1) {
//                endIndex = i;
//                break;
//            }
//        }
//
//        RimeDictData result = new RimeDictData();
//
//        if (startIndex != -1 && endIndex != -1) {
//            // 解析 YAML 头部
//            List<String> yamlLines = lines.subList(startIndex, endIndex + 1);
//            String yamlContent = String.join("\n", yamlLines);
//            result.setHeader(parseYamlHeader(yamlContent));
//
//            // 解析词条（从 endIndex + 1 开始）
//            List<String> entryLines = new ArrayList<>();
//            for (int i = endIndex + 1; i < lines.size(); i++) {
//                entryLines.add(lines.get(i));
//            }
//            result.setEntries(parseEntries(entryLines));
//        } else {
//            // 没有 "---" 或 "..."，可能整个文件就是词条列表
//            result.setEntries(parseEntries(lines));
//        }
//
//        return result;
//    }
//
//    /**
//     * 解析 Rime 词库文件
//     * @param filePath 文件路径
//     * @return 包含头部信息和词条列表的对象
//     */
//    public RimeDictData parseRimeDict(Path filePath) throws IOException {
//        List<String> lines = Files.readAllLines(filePath, java.nio.charset.StandardCharsets.UTF_8);
//
//        // 1. 找到分隔线 "..." 的位置
//        int separatorIndex = -1;
//        for (int i = 0; i < lines.size(); i++) {
//            String line = lines.get(i).trim();
//            if (line.equals("...")) {
//                separatorIndex = i;
//                break;
//            }
//        }
//
//        RimeDictData result = new RimeDictData();
//
//        if (separatorIndex >= 0) {
//            // 2. 解析 YAML 头部（从 "---" 到 "..." 之前）
//            List<String> yamlLines = lines.subList(0, separatorIndex + 1);
//            String yamlContent = String.join("\n", yamlLines);
//            Map<String, Object> header = parseYamlHeader(yamlContent);
//            result.setHeader(header);
//
//            // 3. 解析词条数据（"..." 之后的所有行）
//            List<String> entryLines = lines.subList(separatorIndex + 1, lines.size());
//            List<DictionaryEntry> entries = parseEntries(entryLines);
//            result.setEntries(entries);
//        } else {
//            // 如果没有 "..."，可能整个文件就是词条数据
//            List<DictionaryEntry> entries = parseEntries(lines);
//            result.setEntries(entries);
//        }
//
//        return result;
//    }
//
//    /**
//     * 解析 YAML 头部
//     */
//    private Map<String, Object> parseYamlHeader(String yamlContent) {
//        Yaml yaml = new Yaml();
//        try {
//            return yaml.load(yamlContent);
//        } catch (Exception e) {
//            System.err.println("解析 YAML 头部失败: " + e.getMessage());
//            return new HashMap<>();
//        }
//    }
//
//    /**
//     * 解析词条数据（纯文本格式）
//     */
//    private List<DictionaryEntry> parseEntries(List<String> lines) {
//        List<DictionaryEntry> entries = new ArrayList<>();
//        int lineNumber = 0;
//
//        for (String line : lines) {
//            lineNumber++;
//            String trimmed = line.trim();
//
//            // 跳过空行和注释
//            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
//                continue;
//            }
//
//            // 词条格式：词\t编码\t权重
//            String[] parts = line.split("\t");
//            if (parts.length >= 2) {
//                String word = parts[0].trim();
//                String code = parts[1].trim();
//                int weight = parts.length >= 3 ? Integer.parseInt(parts[2].trim()) : 1;
////                entries.add(new DictionaryEntry(word, code, weight, lineNumber, DictionaryType.BASE_DICT));
//                entries.add(new DictionaryEntry());
//            }
//        }
//
//        return entries;
//    }
//
//    /**
//     * 数据封装类
//     */
//    public static class RimeDictData {
//        private Map<String, Object> header = new HashMap<>();
//        private List<DictionaryEntry> entries = new ArrayList<>();
//
//        public Map<String, Object> getHeader() { return header; }
//        public void setHeader(Map<String, Object> header) { this.header = header; }
//
//        public List<DictionaryEntry> getEntries() { return entries; }
//        public void setEntries(List<DictionaryEntry> entries) { this.entries = entries; }
//
//        public String getName() {
//            return header.containsKey("name") ? header.get("name").toString() : "";
//        }
//
//        public String getVersion() {
//            return header.containsKey("version") ? header.get("version").toString() : "";
//        }
//    }
//}