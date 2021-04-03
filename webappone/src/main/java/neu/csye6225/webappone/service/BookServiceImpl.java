package neu.csye6225.webappone.service;

import com.timgroup.statsd.StatsDClient;
import neu.csye6225.webappone.aws.SNSService;
import neu.csye6225.webappone.controller.BookController;
import neu.csye6225.webappone.dao.BookDao;
import neu.csye6225.webappone.dao.UserDao;
import neu.csye6225.webappone.pojo.Book;
import neu.csye6225.webappone.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StatsDClient statsd;
    @Autowired
    private SNSService snsService;
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book save(Book book) {
        long startTime = System.currentTimeMillis();
        Book res = bookDao.save(book);
        statsd.recordExecutionTime("DB Response Time - Save Book", System.currentTimeMillis() - startTime);
        String userEmail = userDao.findById(book.getUser_id()).getUsername();
        snsService.postToTopic("POST", userEmail, book.getId(), book.getTitle(), book.getAuthor());
        return res;
    }

    @Override
    public Book findById(String id) {
        long startTime = System.currentTimeMillis();
        Book res = bookDao.findById(id);
        statsd.recordExecutionTime("DB Response Time - Find Book By Id", System.currentTimeMillis() - startTime);
        return res;
    }

    @Override
    public Book findByIsbn(String isbn) {
        long startTime = System.currentTimeMillis();
        Book res = bookDao.findByIsbn(isbn);
        statsd.recordExecutionTime("DB Response Time - Find Book By ISBN", System.currentTimeMillis() - startTime);
        return res;
    }

    @Override
    public List<Book> findAll() {
        long startTime = System.currentTimeMillis();
        List<Book> res = bookDao.findAll();
        statsd.recordExecutionTime("DB Response Time - Find All Books", System.currentTimeMillis() - startTime);
        return res;
    }

    @Override
    public void deleteById(String id) {
        long startTime = System.currentTimeMillis();
        bookDao.deleteById(id);
        statsd.recordExecutionTime("DB Response Time - Delete Book", System.currentTimeMillis() - startTime);
        Book book = bookDao.findById(id);
        String userId = book.getUser_id();
        logger.info(userId);
        User user = userDao.findById(userId);
        String userEmail = user.getUsername();
        logger.info(userEmail);
        snsService.postToTopic("DELETE", userEmail, book.getId(), book.getTitle(), book.getAuthor());
    }


}
