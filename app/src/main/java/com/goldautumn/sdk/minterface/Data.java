//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data {
    public Data() {
    }

    public static class BaseData {
        public Map<String, String> m_attMap;

        public BaseData() {
            if(this.m_attMap == null) {
                this.m_attMap = new HashMap();
            }

        }

        public void SetData(String attName, String attValue) {
            this.m_attMap.put(attName, attValue);
        }

        public HashMap<String, String> GetHashMap() {
            return (HashMap)this.m_attMap;
        }

        public String[] GetStringArray(String AttName) {
            Log.e("type", " get data :" + this.GetData(AttName));
            if(this.GetData(AttName) != "" && !this.GetData(AttName).isEmpty()) {
                JSONArray jValue = null;

                try {
                    jValue = new JSONArray(this.GetData(AttName));
                } catch (JSONException var7) {
                    var7.printStackTrace();
                }

                String[] strs = new String[jValue.length()];

                for(int i = 0; i < jValue.length(); ++i) {
                    try {
                        Log.e("type", "Value" + i + jValue.get(i));
                        strs[i] = jValue.get(i).toString();
                    } catch (JSONException var6) {
                        var6.printStackTrace();
                    }
                }

                return strs;
            } else {
                Log.e("type", "is null");
                return new String[0];
            }
        }

        public String GetData(String attName) {
            String value = (String)this.m_attMap.get(attName);
            if(value == null) {
                this.m_attMap.put(attName, "");
                value = "";
            }

            return value;
        }

        public int GetInt(String attName) {
            String value = this.GetData(attName);
            return "" != value?Integer.parseInt(value):0;
        }

        public float GetFloat(String AttName) {
            String value = this.GetData(AttName);
            return "" != value?Float.parseFloat(value):0.0F;
        }

        public boolean GetBool(String attName) {
            int value = this.GetInt(attName);
            return value != 0;
        }

        public void CopyAtt(Data.BaseData _in_data, String _in_att_name) {
            this.SetData(_in_att_name, _in_data.GetData(_in_att_name));
        }

        public void CopyAttByData(Data.BaseData _in_data) {
            Iterator var3 = _in_data.m_attMap.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, String> entry = (Entry)var3.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                this.SetData(key, value);
            }

        }

        public String DataToString() {
            String outStr = "";
            JSONObject jsData = new JSONObject();
            Iterator var4 = this.m_attMap.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, String> entry = (Entry)var4.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();

                try {
                    jsData.put(key, value);
                } catch (JSONException var8) {
                    throw new RuntimeException(var8);
                }
            }

            outStr = outStr + jsData.toString();
            return outStr;
        }

        public void StringToData(String _in_str) {
            this.m_attMap.clear();

            try {
                JSONObject attJS = new JSONObject(_in_str);
                Iterator it = attJS.keys();

                while(it.hasNext()) {
                    String attName = it.next().toString();
                    String attValue = attJS.getString(attName);
                    this.SetData(attName, attValue);
                }
            } catch (JSONException var6) {
                ;
            }

        }
    }

    public static class LoginOrRegisterData extends Data.BaseData {
        public LoginOrRegisterData() {
        }
    }

    public static class MEventData extends Data.BaseData {
        public MEventData() {
        }
    }

    public static class PayRusltData extends Data.BaseData {
        public PayRusltData() {
        }
    }

    public static class PayTypeData extends Data.BaseData {
        public PayTypeData() {
        }
    }

    public static class PhonData extends Data.BaseData {
        public PhonData() {
        }
    }

    public static class ShareData extends Data.BaseData {
        public ShareData() {
        }
    }

    public static class UserCenterData extends Data.BaseData {
        public UserCenterData() {
        }
    }
}
