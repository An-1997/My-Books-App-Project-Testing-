package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.repository.BookRepository;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	
	@Mock
	private transient BookRepository bookRepo;
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	transient Optional<Book> options;
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void saveBookTest() throws BookAlreadyExistException {
		Book book = new Book();
		book.setId(101);
		book.setTitle("Da Vinci Code");
		book.setCoverImage("abc330");
		book.setFirstPublishYear(2006);
		book.setUserId("ab302");
		final boolean flag = bookServiceImpl.saveBookToMyFavourites(book);
		assertTrue(flag);
	}
	@Test(expected=BookAlreadyExistException.class)
	public void saveBookTestFailure() throws BookAlreadyExistException {
		Book book = new Book();
		book.setId(101);
		book.setTitle("Da Vinci Code");
		book.setCoverImage("abc330");
		book.setFirstPublishYear(2006);
		book.setUserId("ab302");
		options=Optional.of(book);
		when( bookRepo.findByUserIdAndTitle(Mockito.anyString(),Mockito.anyString())).thenReturn(options);
		bookServiceImpl.saveBookToMyFavourites(book);
		verify(bookRepo).findByUserIdAndTitle(Mockito.anyString(),Mockito.anyString()
					);
	}
	@Test
	public void getAllBookTest() {
		final List<Book> bookList = new ArrayList<>();
		Book book = new Book();
		book.setId(1);
		book.setTitle("venkey");
		book.setCoverImage("abc33");
		book.setFirstPublishYear(2001);
		book.setUserId("ab30");
		bookList.add( book );
		when(bookRepo.findByUserId("ab30")).thenReturn(bookList);
		List<Book> fetchedBook = bookServiceImpl.getMyFavouriteBooks("ab30");
		assertEquals(bookList,fetchedBook);
		
	}
	
	@Test
	public void deleteBookByIdSucessTest() throws BookNotFoundException {
		Book book = new Book();
		book.setId(101);
		book.setUserId("abcd");
		book.setCoverImage("bookImage.jpg");
		book.setTitle("Harry Potter");
		book.setFirstPublishYear(2004);
		book.setEditionCount(5);
		options=Optional.of(book);
		when(bookRepo.findById(101)).thenReturn(options);
		final boolean flag = bookServiceImpl.deleteBookFromMyFavourites(101);
		assertTrue(flag);
	}
	
	@Test(expected=BookNotFoundException.class)
	public void deleteBookByIdTestException() throws BookNotFoundException {
		Book book = new Book();
		book.setId(101);
		book.setUserId("abcd");
		book.setCoverImage("bookImage.jpg");
		book.setTitle("Harry Potter");
		book.setFirstPublishYear(2004);
		book.setEditionCount(5);
		options=Optional.of(book);
		when(bookRepo.findById(101)).thenReturn(options);
		 bookServiceImpl.deleteBookFromMyFavourites(102);
		}
	

	
}
