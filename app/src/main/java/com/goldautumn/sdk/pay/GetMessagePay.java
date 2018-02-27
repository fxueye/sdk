//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import java.util.List;

public class GetMessagePay {
    private List<GetMessagePay.Message> messages;

    public GetMessagePay() {
    }

    public List<GetMessagePay.Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<GetMessagePay.Message> messages) {
        this.messages = messages;
    }

    public static class Message {
        private String time;
        private String name;
        private String price;
        private String ruslt;
        private String status;

        public Message() {
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRuslt() {
            return this.ruslt;
        }

        public void setRuslt(String ruslt) {
            this.ruslt = ruslt;
        }
    }
}
