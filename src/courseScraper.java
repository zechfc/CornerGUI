import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class courseScraper {
    public static void main(String[] args) throws IOException {
        // Step 1: Scrape learning outcomes
        String learningOutcomes = scrapeLearningOutcomes();

        // Step 2: Parse existing JSON data
        Gson gson = new Gson();
        
        Course course = gson.fromJson(new FileReader("src/courses copy.json"), Course.class);

        // Step 3: Merge data
        course.setLearningOutcomes(learningOutcomes);

        // Step 4: Write updated JSON
        try (FileWriter writer = new FileWriter("updated_courses.json")) {
            gson.toJson(course, writer);
        }
    }

    private static String scrapeLearningOutcomes() throws IOException {
        // Use Jsoup to scrape learning outcomes from the website
        Document doc = Jsoup.connect("https://academicbulletins.sc.edu/undergraduate/carolina-core-courses/#foundationalcoursestext").get();
        Elements outcomes = doc.select(".even filterRow selected");
        String output=outcomes.text();
        // Extract learning outcomes from HTML elements
        // You may need to refine the selection based on the actual structure of the webpage
        // For simplicity, I'm assuming each learning outcome is within a <div> with class "course-title"
        // You might need to adjust this based on the actual structure of the webpage
        // Convert the extracted outcomes to a list of strings
        // Here, I'm just using the text content of the elements as learning outcomes
        return output;
    }
}