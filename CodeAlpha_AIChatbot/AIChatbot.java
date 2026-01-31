import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class AIChatbot extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private HashMap<String, String> faq;

    public AIChatbot() {
        // Frame 
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input field
        inputField = new JTextField();
        sendButton = new JButton("Send");

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        trainBot();
        sendButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());
    }
    // Train chatbot with Questions
    private void trainBot() {
        faq = new HashMap<>();
        faq.put("what is java", "Java is an object-oriented programming language.");
        faq.put("what is ai", "Artificial Intelligence enables machines to think like humans.");
        faq.put("what is machine learning", "Machine Learning allows systems to learn from data.");
        faq.put("who are you", "I am an AI chatbot built using Java.");
        faq.put("hello", "Hello! How can I help you?");
        faq.put("hi", "Hi there! How can I assist you?");
        faq.put("bye", "Goodbye! Have a nice day.");
    }
  // Processs users input
    private void processInput() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");
        inputField.setText("");

        String response = getBotResponse(userInput);
        chatArea.append("Bot: " + response + "\n\n");
    }
    // NLP + Rule-based logic
    private String getBotResponse(String input) {
        input = input.toLowerCase();
        input = input.replaceAll("[^a-z ]", "");
        for (String question : faq.keySet()) {
            if (input.contains(question)) {
                return faq.get(question);
            }}
        // Default response
return "Sorry, I didn't understand that. Please ask something else.";
    }
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AIChatbot bot = new AIChatbot();
            bot.setVisible(true);
        });
}}


