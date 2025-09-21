package lk.ijse.b72.finalproject.back_end.service.impl;

import lk.ijse.b72.finalproject.back_end.DTO.UserDTO;
import lk.ijse.b72.finalproject.back_end.Entity.User;
import lk.ijse.b72.finalproject.back_end.repo.UserRepo;
import lk.ijse.b72.finalproject.back_end.service.UserService;
import lk.ijse.b72.finalproject.back_end.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public UserDTO getSelectedUser(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<String> getAllUserIds() {
        List<UUID> userIds = userRepo.findAllUserIds();
        return userIds.stream().map(UUID::toString).toList();
    }

    @Override
    public int save(UserDTO userDTO, String role) {
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(role);
            userRepo.save(user);
            return VarList.OK;
        }
    }

    @Override
    public int deleteUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return VarList.Not_Found;
        }
        userRepo.delete(user);
        return VarList.OK;
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        return save(userDTO, "USER");
    }

    @Override
    public int saveAdmin(UserDTO userDTO) {
        return save(userDTO, "ADMIN");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    public UserDTO loadUserDetailsByUsername(String email) {
        User user = userRepo.findByEmail(email);
        return modelMapper.map(user,UserDTO.class);
    }
}