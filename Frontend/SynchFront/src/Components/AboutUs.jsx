
    import React from 'react';
    import { Link, useNavigate } from 'react-router-dom'; // Import the useNavigate hook


    const AboutUs = () => {

        const navigate = useNavigate(); // Call the hook

        const handleGoBack = () => { // Create a handler function
            navigate(-1); // Navigate back to the previous page
        };

        return (
            <div className="container mx-auto p-8">
                 <div className="flex items-center mb-4">
                     <button
                        className="text-blue-500 hover:underline"
                        onClick={handleGoBack}
                     >
                        &larr; Back
                    </button>
                </div>
                <h1 className="text-4xl font-bold mb-4">About Us</h1>
                <p>
                We are passionate junior students at Bilkent University, dedicated to programming and innovation.
                </p>

                <h2 className="text-2xl font-bold mt-8 mb-4">Our Team</h2>

                <div className="grid grid-cols-2 gap-4">
                    {/* Team Member 1 */}
                    <div className="text-center">
                        <img
                            src="https://media.licdn.com/dms/image/C4D03AQFnedyongjCEw/profile-displayphoto-shrink_800_800/0/1668101973946?e=1706140800&v=beta&t=Rrw9xDrS-k7l0eLVvwz5VmWuNslnoFhzLlqQi6QfdAI"
                            alt="Team Member 1"
                            className="rounded-full w-32 h-32 mx-auto mb-2"
                        />
                        <h3 className="text-lg font-bold">Tuna Saygın</h3>
                        <p>Fullstack Developer</p>
                        <a
                            href="https://www.linkedin.com/in/tuna-sayg%C4%B1n-419941241/"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-blue-500 hover:underline"
                        >
                            LinkedIn
                        </a>
                    </div>

                    {/* Team Member 2 */}
                    <div className="text-center">
                        <img
                            src="https://media.licdn.com/dms/image/C4D03AQFKAVqS7sa_lA/profile-displayphoto-shrink_200_200/0/1649440193142?e=1706140800&v=beta&t=6HZgt2yM-bMA7xlfQon3uCdjhHnatoSkO58GjjI2lCk"
                            alt="Team Member 2"
                            className="rounded-full w-32 h-32 mx-auto mb-2"
                        />
                        <h3 className="text-lg font-bold">Hüseyin Burhan Tabak</h3>
                        <p>Backend Developer</p>
                        <a
                            href="https://www.linkedin.com/in/hburhantabak/"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-blue-500 hover:underline"
                        >
                            LinkedIn
                        </a>
                    </div>

                    {/* Team Member 3 */}
                    <div className="text-center">
                        <img
                            src="https://media.licdn.com/dms/image/D4D03AQHyFv91mjciAw/profile-displayphoto-shrink_200_200/0/1692299115939?e=1706140800&v=beta&t=hpYiydgp_Q1ZFoVYQwY-cLi4C6hUEKSO_2S0GdUw9q8"
                            alt="Team Member 3"
                            className="rounded-full w-32 h-32 mx-auto mb-2"
                        />
                        <h3 className="text-lg font-bold">Kanan Zeynalov</h3>
                        <p>Frontend Developer</p>
                        <a
                            href="https://www.linkedin.com/in/kananzeynalov07/"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-blue-500 hover:underline"
                        >
                            LinkedIn
                        </a>
                    </div>

                    {/* Team Member 4 */}
                    <div className="text-center">
                        <img
                            src="https://media.licdn.com/dms/image/C4E03AQHfgthseLIgeQ/profile-displayphoto-shrink_200_200/0/1634917865333?e=1706140800&v=beta&t=T4mPsyOMiKUZ8a1x8fr1kC2lnFkPwfIZDIGVMmItSAU"
                            alt="Team Member 4"
                            className="rounded-full w-32 h-32 mx-auto mb-2"
                        />
                        <h3 className="text-lg font-bold">Işıl Özgü</h3>
                        <p>Backend Developer</p>
                        <a
                            href="https://www.linkedin.com/in/isilozgu/"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-blue-500 hover:underline"
                        >
                            LinkedIn
                        </a>
                    </div>

                    {/* Team Member 5 */}
                    <div className="text-center">
                        <img
                            src="https://media.licdn.com/dms/image/D4D03AQHZwUnvSaPpQg/profile-displayphoto-shrink_200_200/0/1673429310545?e=1706140800&v=beta&t=gjunIpeWSZ5OItjjTYDy2vb4HL9pVLPuKnGMb564XVY"
                            alt="Team Member 5"
                            className="rounded-full w-32 h-32 mx-auto mb-2"
                        />
                        <h3 className="text-lg font-bold">Ahmet Tarık Uçur</h3>
                        <p>Backend Developer</p>
                        <a
                            href="https://www.linkedin.com/in/tarikucur/"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-blue-500 hover:underline"
                        >
                            LinkedIn
                        </a>
                    </div>
                </div>
            </div>
        );
    };

 

export default AboutUs;
