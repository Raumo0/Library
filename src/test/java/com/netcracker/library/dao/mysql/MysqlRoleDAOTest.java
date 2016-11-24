package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.users.Role;
import com.netcracker.library.beans.users.User;
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
    private static DAOFactory factory;
    private static RoleDAO roleDAO;
    private Role role;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        roleDAO = factory.getRoleDAO();
    }

    @Before
    public void setUp() throws Exception {
        role = new Role();
        role.setName("role_name" + counter + date.getTime());
        counter += 1;
        role.setDescription("description");
        role.setId(roleDAO.insert(role));
    }

    @After
    public void tearDown() throws Exception {
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
        Role role2;
        boolean deleted = roleDAO.deleteById(role.getId());
        Assert.assertTrue(deleted);
        role2 = roleDAO.getById(role.getId());
        Assert.assertTrue(!role.equals(role2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(roleDAO.deleteAll());
        Assert.assertTrue(roleDAO.getAll().size() == 0);
        List<Role> roles = new LinkedList<>();
        Role newRole;
        for (int i = 0; i < 10; i++){
            newRole = new Role(role);
            newRole.setName("role_name" + counter + date.getTime());
            counter += 1;
            newRole.setId(roleDAO.insert(newRole));
            roles.add(newRole);
        }
        Assert.assertTrue(roleDAO.getAll().size() == roles.size());
        Assert.assertTrue(roleDAO.deleteAll());
        Assert.assertTrue(roleDAO.getAll().size() == 0);
    }

    @Test
    public void getRoleByUserId() throws Exception {
        User user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        user.setUsername("username" + counter + date.getTime());
        user.setPassword("password");
        user.setSalt("salt");
        user.setRole(role);
        user.setId(factory.getUserDAO().insert(user));
        counter += 1;
        Assert.assertEquals(role, roleDAO.getRoleByUserId(user.getId()));
    }
}