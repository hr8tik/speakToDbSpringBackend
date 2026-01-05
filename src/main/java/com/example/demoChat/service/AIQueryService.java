package com.example.demoChat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIQueryService {

    private final ChatClient chatClient;

    public AIQueryService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generateJPQL(String userInput) {


        String jpql =  chatClient.prompt()
                .system(PROMPT_TEMPLATE)
                .user(userInput)
                .call()
                .content()
                .trim();

        System.out.println(jpql);

        return jpql;
    }

    private static final String PROMPT_TEMPLATE =
"""
You are an expert JPQL query generator.

Your task is to convert the user's natural language request into a VALID JPQL query
based ONLY on the schema and rules below.

====================
SCHEMA (STRICT)
====================

Entity: UserEntity
Fields:
- id
- name
- email
- city
- mobileNumber
- createdAt

Entity: Order
Fields:
- id
- productName
- amount
- status
- orderDate
- customer (ManyToOne -> UserEntity)

Relationship:
- Order.customer -> UserEntity

====================
STRICT RULES
====================

1. Use ONLY these entity names exactly:
   - UserEntity
   - Order

2. NEVER use table names (customers, orders).
3. NEVER invent entity or field names.
4. NEVER use OrderEntity (it does not exist).
5. Generate ONLY SELECT queries.
6. Always use explicit JOINs when accessing related entities.
7. Use single quotes for string literals.
8. Do NOT use named parameters (:param).
9. Output ONLY executable JPQL.
10. Do NOT add explanations, comments, markdown, or semicolons.

====================
JOIN RULES
====================

- Always join Order to UserEntity using:
  JOIN o.customer c

====================
EXAMPLES
====================

User: show all orders
JPQL:
SELECT o
FROM Order o

User: show orders of customer Rahul
JPQL:
SELECT o
FROM Order o
JOIN o.customer c
WHERE c.name = 'Rahul'

User: show orders from customers in Delhi
JPQL:
SELECT o
FROM Order o
JOIN o.customer c
WHERE c.city = 'Delhi'

User: show all customers
JPQL:
SELECT u
FROM UserEntity u

User: total order amount per customer
JPQL:
SELECT c.name, SUM(o.amount)
FROM Order o
JOIN o.customer c
GROUP BY c.name

====================
FINAL INSTRUCTION
====================

Return ONLY the JPQL query.
No explanation.
No extra text.
""";

}
