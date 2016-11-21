package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.users.Role;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.RoleDAO;
import org.junit.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 21.11.16.
 */
public class MysqlRoleDAOTest {
    private DAOFactory factory;
    private RoleDAO roleDAO;
    private Role role;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
    }

    @Before
    public void setUp() throws Exception {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        roleDAO = factory.getRoleDAO();
        role = new Role();
        role.setName("role_name" + counter + date.getTime());
        counter += 1;
        role.setDescription("description");
        role.setId(roleDAO.insert(role));
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        roleDAO = null;
        role = null;
    }

    @Test
    public void insert() throws Exception {
        Role role2;
        role2 = roleDAO.getById(role.getId());
        Assert.assertEquals(role, role2);
    }

    @Test
    public void update() throws Exception {
        Role role2;
        role2 = roleDAO.getById(role.getId());
        Assert.assertTrue(role.equals(role2));
        Assert.assertNotSame(role, role2);
        role2.setDescription("newDescription");
        role.setDescription("newDescription");
        roleDAO.update(role2);
        Assert.assertEquals(role, roleDAO.getById(role2.getId()));
    }

    @Test
    public void deleteById() throws Exception {
        Role author2;
        boolean deleted = roleDAO.deleteById(role.getId());
        Assert.assertTrue(deleted);
        author2 = roleDAO.getById(role.getId());
        Assert.assertTrue(!role.equals(author2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(roleDAO.deleteAll());
        Assert.assertTrue(roleDAO.getAll().size() == 0);
        List<Role> bookEditions = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            Role newBookEdition = new Role(role);
            role.setName("role_name" + counter + date.getTime());
            counter += 1;
            newBookEdition.setId(roleDAO.insert(newBookEdition));
            bookEditions.add(newBookEdition);
        }
        Assert.assertTrue(roleDAO.getAll().size() == bookEditions.size());
        Assert.assertTrue(roleDAO.deleteAll());
        Assert.assertTrue(roleDAO.getAll().size() == 0);
    }

}