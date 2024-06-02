<h1>Traveller's Guild [ONGOING PROJECT]</h1>

<p>Hi! This is a pretty ambitious project that is still in the making, with the aim of helping the user with the self-reflection in terms of relating to fictional characters and interacting with fictional content.</p>
<p>The intention is for the user to input the media they are currently consuming, to note their favourite characters and their traits, and then analyse the data provided.</p>
<p>Currently, the app is capable of handling the input of the user's favourite characters, as well as validating the given data.</p>

<h2>Features</h2>

<h3>Backend (Spring Boot)</h3>
<ul>
    <li><strong>Layered Architecture:</strong> The backend is structured using a layered architecture pattern, with separate layers for controllers, services, repositories, and models. This promotes modularity, maintainability, and separation of concerns.</li>
    <li><strong>Encapsulation:</strong> Business logic and data access are encapsulated within service classes, promoting code organization and reusability.</li>
    <li><strong>RESTful API:</strong> The backend provides a robust and scalable RESTful API for managing characters and accessing analysis data.</li>
    <li><strong>Data Validation:</strong> Input data is validated using Spring's validation framework to ensure data integrity and security.</li>
    <li><strong>Error Handling:</strong> Comprehensive error handling mechanisms are implemented to provide meaningful error messages and handle exceptions gracefully.</li>
   </ul>

<h3><h3><a href="https://github.com/FintinaOlivia/Travellers-Guild-Frontend">Frontend (React)</a></h3>
</h3>
<ul>
    <li><strong>Component-Based Architecture:</strong> The frontend is developed using React, following a component-based architecture. Each UI element is encapsulated within a reusable component, promoting code modularity and reusability.</li>
    <li><strong>State Management:</strong> State management is handled efficiently using Redux, ensuring predictable state behavior and efficient UI updates.</li>
    <li><strong>Responsive Design:</strong> The frontend is designed to be responsive and mobile-friendly, providing a seamless user experience across devices and screen sizes.</li>
    <li><strong>UI/UX Best Practices:</strong> User interface and user experience design principles are followed to create an intuitive and visually appealing interface, enhancing user engagement and satisfaction.</li>
    <li><strong>Code Splitting:</strong> Code splitting techniques are employed to optimize performance and reduce initial load times, ensuring a smooth user experience.</li>
    <li><strong>Error Handling:</strong> Frontend components include error handling mechanisms to gracefully handle unexpected errors and provide informative error messages to users.</li>
    <li><strong>Testing:</strong> Unit tests tests are implemented to ensure the reliability and stability of frontend components, following testing best practices.</li>
</ul>

<h2>Bonus Features</h2>

<h3>Backend</h3>
<ul>
    <li><strong>Autogeneration of Data:</strong> The backend uses the Faker library to autogenerate data, in order to monitor, test and improve the user experience by testing the response times and behaviour of the application when it comes to large datasets.</li>
   </ul>

<h3>Frontend</h3>
<ul>
    <li><strong>Charting:</strong> The frontend includes charting features to visualize character data and trends, making it easier for users to interpret the analysis results.</li>
    <li><strong>Offline Warnings:</strong> The frontend detects server or internet connectivity issues and provides warnings to the user, ensuring a seamless experience even in challenging network conditions.</li>

</ul>
