package com.jasonstudio.jy.androidlibpractice.service.ap5630w;

import com.jasonstudio.jy.androidlib.http.mock.MockManager;

import java.util.HashMap;
import java.util.Map;

public class Ap5630wMockManager implements MockManager {

    private static Map<String, String> DUMMY_DATA_MAP = new HashMap<>();

    static {
        DUMMY_DATA_MAP.put("GET-LOGIN", "{\n" +
                "        \"status\": \"success\",\n" +
                "        \"token\": \"sojaofhaognaogod8halgnoasfoasfiohasgnoasgnaog\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-LOGOUT", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-REBOOT", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-ACCOUNT_PWD", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-WIFI_BASIC_RELOAD", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-WIFI_BASIC_RELOAD_WLAN", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-WIFI_BASIC", "{\n" +
                "        \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-ENCRY_OPTIONS", "{\n" +
                "   \"status\": \"success\"\n" +
                "   \"encry_options\":\n" +
                "   [\n" +
                "     {\n" +
                "        \"name\": \"WPA2 personal\",\n" +
                "        \"value\": 3\n" +
                "     },\n" +
                "     {\n" +
                "        \"name\": \"WPA Mix Mode Personal\",\n" +
                "        \"value\":4\n" +
                "     }\n" +
                "   ]\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-ONBOARD_STATUS", "{\n" +
                "   \"onboarded\": true\n" +
                "   \"default_pwd\": \"admin\"\n" +
                "   \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-ONBOARD_STATUS", "{\n" +
                "   \"status\": \"success\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-GLOBAL_DATA", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"firmware_version\": \"1.0.8\",\n" +
                "    \"current_client\":\"AA:BB:CC:CL:IE:01\",\n" +
                "    \"device_num_per_page\":10,\n" +
                "    \"router_mode\":1,\n" +
                "    \"mix_mode\":0,\n" +
                "    \"device_type\":\"Mesh\",\n" +
                "    \"mesh_mode\":2,\n" +
                "    \"mesh_mode_list\":[ \n" +
                "        {\n" +
                "            \"name\":\"RE\",\n" +
                "            \"value\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"CAP\",\n" +
                "            \"value\":2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"router_mode_list\":[\n" +
                "        {\n" +
                "            \"name\":\"Router\",\n" +
                "            \"value\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"mix_mode_list\":[\n" +
                "        {\n" +
                "            \"name\":\"Router\",\n" +
                "            \"value\":0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"about\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"title\":\"Terms & condition\",\n" +
                "            \"url\":\"https://askeycloud.askey.com/owncloud/index.php/s/a8jnEqrw2XdXAWa\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"Privacy policy\",\n" +
                "            \"url\":\"https://askeycloud.askey.com/owncloud/index.php/s/TDVInWAwwVz9jTr\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"wait_time\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"API\":\"GET-GLOBAL_DATA\",\n" +
                "            \"wait_time\":30\n" +
                "        },\n" +
                "        {\n" +
                "            \"API\":\"GET-REBOOT\",\n" +
                "            \"wait_time\":10\n" +
                "        }\n" +
                "    ],\n" +
                "    \"time_out\":\n" +
                "    {\n" +
                "        \"system_using_time\":30,\n" +
                "        \"API_wait_time\":30\n" +
                "    },\n" +
                "    \"disconnect_api\":\n" +
                "    [\n" +
                "        \"GET-REBOOT\",\"PUT-WIFI_BASIC\"\n" +
                "    ],\n" +
                "    \"support_feature\":[\n" +
                "        {\n" +
                "            \"title\":\"wifi\",\n" +
                "            \"sub_title\":\n" +
                "            [\n" +
                "                \"network\",\n" +
                "                \"advance\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"wifi\":\n" +
                "    {\n" +
                "        \"guest_wifi\":\n" +
                "        {\n" +
                "            \"support\":true,\n" +
                "            \"support_number\":1\n" +
                "        },\n" +
                "        \"radio_list\":\n" +
                "        [\n" +
                "            {\n" +
                "                \"name\": \"2.4G\",\n" +
                "                \"inter_value\": 0,\n" +
                "                \"support\":\"read_write\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"5G\",\n" +
                "                \"inter_value\": 1,\n" +
                "                \"support\":\"read_write\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"security_list\":\n" +
                "        [\n" +
                "            {\n" +
                "                \"name\": \"WPA2 AES\",\n" +
                "                \"value\":0\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"WPA MIX TKIP + AES\",\n" +
                "                \"value\":1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"channel_list\": \n" +
                "        [\n" +
                "            {\n" +
                "                \"inter_value\":0,\n" +
                "                \"list\":[1,2,3,5,6,7,8,9,10,11,12,13]\n" +
                "            },\n" +
                "            {\n" +
                "                \"inter_value\":1,\n" +
                "                \"list\":[36,40,44,48,100,104,108,112,136,140,144,148]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"channel_setting\":\n" +
                "        {\n" +
                "            \"auto_mode\":\"enable_setting\",\n" +
                "            \"manual_mode\":\"enable_setting\"\n" +
                "        },\n" +
                "        \"ssids\":\n" +
                "        [\n" +
                "            {\n" +
                "                \"inter_value\":0,\n" +
                "                \"list\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":\"AP5630W_2.4G\",\n" +
                "                        \"name\":\"AP5630W_2.4G\",\n" +
                "                        \"support\":\"true\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"inter_value\":1,\n" +
                "                \"list\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":\"AP5630W_5G\",\n" +
                "                        \"name\":\"AP5630W_5G\",\n" +
                "                        \"support\":\"true\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bandwidth_options\": \n" +
                "        [\n" +
                "            {\n" +
                "                \"inter_value\":0,\n" +
                "                \"list\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"name\":\"OPTION1\",\n" +
                "                        \"value\":0,\n" +
                "                        \"show_channel\": [1,3,5,7,9]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\":\"OPTION2\",\n" +
                "                        \"value\":1,\n" +
                "                        \"show_channel\": [2,4,6,8,10]\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"inter_value\":1,\n" +
                "                \"list\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"name\":\"OPTION1\",\n" +
                "                        \"value\":0,\n" +
                "                        \"show_channel\": [36,40,44,48]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\":\"OPTION2\",\n" +
                "                        \"value\":1,\n" +
                "                        \"show_channel\": [100,112,128,136]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    } \n" +
                "}");

        DUMMY_DATA_MAP.put("GET-CURRENT_DATA", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"wifi\": \n" +
                "    {\n" +
                "        \"interface\": \n" +
                "        [\n" +
                "            {\n" +
                "                \"inter_value\":0, \n" +
                "                \"bandwidth\": 20,\n" +
                "                \"channel\": -1,\n" +
                "                \"current_channel\":12,\n" +
                "                \"ssid\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":0,\n" +
                "                        \"value\": \"if0-0\", \n" +
                "                        \"name\": \"Wifi_2.4G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\":1,\n" +
                "                        \"value\": \"if0-1\", \n" +
                "                        \"name\": \"Guest_2.4G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"inter_value\":1, \n" +
                "                \"bandwidth\": 20,\n" +
                "                \"channel\": -1,\n" +
                "                \"current_channel\":36,\n" +
                "                \"ssid\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":0,\n" +
                "                        \"value\": \"if1-0\", \n" +
                "                        \"name\": \"Wifi_5G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\":1,\n" +
                "                        \"value\": \"if1-1\", \n" +
                "                        \"name\": \"Guest_5G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ] \n" +
                "    },\n" +
                "    \"system\":\n" +
                "    {\n" +
                "        \"lan_ip\":\"192.168.1.10\",\n" +
                "        \"wan_ip\":\"172.100.1.0\",\n" +
                "        \"mac\":\"AA:BB:CC:ME:SH:01\",\n" +
                "        \"dns\":\"dns.example.com\",\n" +
                "        \"alias\":\"Network\",\n" +
                "        \"account\":\"admin\",\n" +
                "        \"password\":\"admin\",\n" +
                "        \"current_mode\":\"CAP\"\n" +
                "    },\n" +
                "    \"network_status\":\"Online\"\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-CURRENT_DATA-WIFI", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"wifi\": \n" +
                "    {\n" +
                "        \"interface\": \n" +
                "        [\n" +
                "            {\n" +
                "                \"inter_value\":0, \n" +
                "                \"bandwidth\": 20,\n" +
                "                \"channel\": -1,\n" +
                "                \"current_channel\":12,\n" +
                "                \"ssid\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":0,\n" +
                "                        \"value\": \"if0-0\", \n" +
                "                        \"name\": \"Wifi_2.4G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\":1,\n" +
                "                        \"value\": \"if0-1\", \n" +
                "                        \"name\": \"Guest_2.4G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"inter_value\":1, \n" +
                "                \"bandwidth\": 20,\n" +
                "                \"channel\": -1,\n" +
                "                \"current_channel\":36,\n" +
                "                \"ssid\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"index\":0,\n" +
                "                        \"value\": \"if1-0\", \n" +
                "                        \"name\": \"Wifi_5G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\":1,\n" +
                "                        \"value\": \"if1-1\", \n" +
                "                        \"name\": \"Guest_5G\", \n" +
                "                        \"security\": 3,\n" +
                "                        \"password\": \"12345678\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ] \n" +
                "    }\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-TABLE_DATA-DEVICE_COUNT", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"mesh_dev_count\":3,\n" +
                "    \"client_dev_count\":10,\n" +
                "    \"parental_control_count\":5\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-TABLE_DATA-CLIENT_LIST", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"client_list\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"name\":\"iPhone 6\",\n" +
                "            \"rssi\":4,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:01\",\n" +
                "            \"connect_type\":\"2.4G\",\n" +
                "            \"index\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"Zenfone\",\n" +
                "            \"rssi\":3,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:02\",\n" +
                "            \"connect_type\":\"5G\",\n" +
                "            \"index\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"PC\",\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:03\",\n" +
                "            \"connect_type\":\"eth\",\n" +
                "            \"index\":2\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"Android TV\",\n" +
                "            \"rssi\":2,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:04\",\n" +
                "            \"connect_type\":\"5G-2\",\n" +
                "            \"index\":3\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"Smart Light\",\n" +
                "            \"rssi\":1,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:05\",\n" +
                "            \"connect_type\":\"2.4G\",\n" +
                "            \"index\":4\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-TABLE_DATA-CLIENT_LIST", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"client_list\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"name\":\"iPhone 6\",\n" +
                "            \"rssi\":4,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:01\",\n" +
                "            \"connect_type\":\"2.4G\",\n" +
                "            \"index\":0\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"Zenfone\",\n" +
                "            \"rssi\":3,\n" +
                "            \"mac\":\"AA:BB:CC:CL:IE:02\",\n" +
                "            \"connect_type\":\"5G\",\n" +
                "            \"index\":1\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-TABLE_DATA-TARGET_CLIENT_INFO", "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"target_client_info\":\n" +
                "    {\n" +
                "        \"name\":\"iPhone 6\",\n" +
                "        \"rx\":\"111.0 Kbps\",\n" +
                "        \"tx\":\"888.0 Kbps\",\n" +
                "        \"connect_type\":\"2.4G\",\n" +
                "        \"connect_to\":{\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:01\",\n" +
                "            \"hostname\":\"NODE1\"\n" +
                "        },\n" +
                "        \"ip\":\"192.168.100.2\",\n" +
                "        \"mac\":\"AA:BB:CC:CL:IE:01\"\n" +
                "    }\n" +
                "}");

        DUMMY_DATA_MAP.put("GET-TABLE_DATA-MESH_LIST", "{\n" +
                "    \"mesh_list\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"name\":\"NODE1\",\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:01\",\n" +
                "            \"connect_type\":\"eth\",\n" +
                "            \"mesh_mode\":2\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"NODE2\",\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:02\",\n" +
                "            \"connect_type\":\"wifi\",\n" +
                "            \"rssi\":\"3\",\n" +
                "            \"mesh_mode\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"NODE3\",\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:03\",\n" +
                "            \"connect_type\":\"wifi\",\n" +
                "            \"rssi\":\"3\",\n" +
                "            \"mesh_mode\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"NODE4\",\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:04\",\n" +
                "            \"connect_type\":\"wifi\",\n" +
                "            \"rssi\":\"4\",\n" +
                "            \"mesh_mode\":1\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        DUMMY_DATA_MAP.put("PUT-TABLE_DATA-TARGET_MESH_INFO", "{\n" +
                "    \"target_mesh_info\":\n" +
                "    {\n" +
                "        \"name\":\"NODE2\",\n" +
                "        \"cpu\":10,\n" +
                "        \"memory\":15,\n" +
                "        \"mac\":\"AA:BB:CC:ME:SH:02\",\n" +
                "        \"ip\":\"192.168.1.10\",\n" +
                "        \"auto_ip\":\"true\",\n" +
                "        \"rx\":\"111.0 Kbps\",\n" +
                "        \"tx\":\"200.0 Kbps\",\n" +
                "        \"rssi\":3,\n" +
                "        \"connect_type\":\"wifi\",\n" +
                "        \"connect_to\":{\n" +
                "            \"mac\":\"AA:BB:CC:ME:SH:01\",\n" +
                "            \"hostname\":\"NODE1\"\n" +
                "        },\n" +
                "        \"mesh_mode\":1\n" +
                "    }\n" +
                "}");
    }


    @Override
    public Map<String, String> getDummyDataByApiKey() {
        return DUMMY_DATA_MAP;
    }
}
