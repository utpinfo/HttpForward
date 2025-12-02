# 項目說明

## 1. 啟動與除錯配置（Run/Debug Configurations）

- 使用 **Spring Boot** 配置方式啟動
- 主類：`com.ginko.httpforward.HttpForwardApplication`
- IDEA 免費版（Community）**沒有 Spring Boot 插件**，無法直接點綠三角啟動，需手動配置：

## 2. 產生war

- Maven > Lifecycle > Package

### IDEA Community 版啟動方式

1. 點擊右上角 `Add Configuration...`
2. 點擊 `+` → 選擇 **Application**
3. 填寫以下內容：
    - **Name**: `HttpForwardApplication`
    - **Main class**: `com.ginko.httpforward.HttpForwardApplication`
    - **VM options**（可選）：`-Dfile.encoding=UTF-8`
    - **Program arguments**（一般留空）
    - **Use classpath of module**: 選擇你的項目 module
4. 點擊 OK，即可使用 Run / Debug 按鈕啟動

> 小技巧：配置好後點右上角綠色小錘子旁的下拉箭頭，選擇 `Edit Configurations...` → `Save as Template`，下次新建項目可直接套用。