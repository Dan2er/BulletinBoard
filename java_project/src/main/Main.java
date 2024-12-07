package main;

import model.Post;
import service.PostService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostService postService = new PostService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Bulletin Board ===");
            System.out.println("1. View All Posts");
            System.out.println("2. Add Post");
            System.out.println("3. Update Post");
            System.out.println("4. Delete Post");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    List<Post> posts = postService.getAllPosts();
                    System.out.println("\n=== All Posts ===");
                    for (Post post : posts) {
                        System.out.printf("ID: %d | Title: %s | User ID: %d%n",
                                post.getPostId(), post.getTitle(), post.getUserId());
                    }
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Content: ");
                    String content = scanner.nextLine();
                    if (postService.addPost(userId, title, content)) {
                        System.out.println("Post added successfully!");
                    } else {
                        System.out.println("Failed to add post.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Post ID: ");
                    int postId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter New Content: ");
                    String newContent = scanner.nextLine();
                    if (postService.updatePost(postId, newTitle, newContent)) {
                        System.out.println("Post updated successfully!");
                    } else {
                        System.out.println("Failed to update post.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Post ID: ");
                    int postId = scanner.nextInt();
                    if (postService.deletePost(postId)) {
                        System.out.println("Post deleted successfully!");
                    } else {
                        System.out.println("Failed to delete post.");
                    }
                }
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
