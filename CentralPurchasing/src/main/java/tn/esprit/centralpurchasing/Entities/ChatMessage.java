package tn.esprit.centralpurchasing.Entities;

public class ChatMessage {

        private String content;
        private String sender;
        private MessageType type;

        public ChatMessage() {
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSender() {
            return this.sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public MessageType getType() {
            return this.type;
        }

        public void setType(MessageType type) {
            this.type = type;
        }

        public static enum MessageType {
            CHAT,
            LEAVE,
            JOIN;

            private MessageType() {
            }
        }
    }


