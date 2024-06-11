// package com.employee.employee.service;

// import com.employee.employee.entity.User;
// import com.employee.employee.repository.UserRepository;
// import com.employee.employee.dto.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class UserInfoUserDetailsService implements UserDetailsService {

    
//     private UserRepository userRepository;


//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<User> userInfo = userRepository.findByUserName(username);
//         return userInfo.map(UserInfoUserDetails::new)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//     }
// }



// // package com.employee.employee.service;

// // import com.employee.employee.entity.User;
// // import com.employee.employee.repository.UserRepository;
// // import com.employee.employee.dto.UserInfoUserDetails;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.stereotype.Service;

// // import java.util.Optional;

// // @Service
// // public class UserInfoUserDetailsService implements UserDetailsService {

// //     private final UserRepository userRepository;

// //     @Autowired
// //     public UserInfoUserDetailsService(UserRepository userRepository) {
// //         this.userRepository = userRepository;
// //     }

// //     @Override
// //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// //         Optional<User> userInfo = userRepository.findByUserName(username);
// //         return userInfo.map(UserInfoUserDetails::new)
// //                 .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
// //     }
// // }






// // package com.employee.employee.service;

// // import java.util.Optional;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;

// // import com.employee.employee.entity.User;
// // import com.employee.employee.repository.UserRepository;
// // import com.employee.employee.dto.UserInfoUserDetails;

// // public class UserInfoUserDetailsService implements UserDetailsService{

// //     @Autowired
// //     UserRepository userRepository;

// //     @Override
// //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// //         Optional<User> user_info = userRepository.findByUserName(username);
// //         return user_info.map(UserInfoUserDetails :: new ).orElseThrow(()-> new UsernameNotFoundException("user not found..!"));
// //     }
    
// // }
