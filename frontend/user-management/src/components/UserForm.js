import React, { useState } from 'react';

const UserForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phonenumber, setPhoneNumber] = useState('');
    const [feedback, setFeedback] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setFeedback('Processing...');
        const query = new URLSearchParams({ name, email, phonenumber }).toString();
        const url = `http://localhost:8080/users/add?${query}`;
    
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
            });
    
            if (response.ok) {
                setFeedback('User added successfully');
                setName('');
                setEmail('');
                setPhoneNumber('');
            } else {
                setFeedback('Failed to add user');
            }
        } catch (error) {
            setFeedback('Error: Failed to fetch');
            console.error('Fetch error:', error);
        }
    };
    

    return (
        <div className="container mt-4">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="Name"
                        className="form-control"
                        required
                    />
                </div>
                <div className="mb-3">
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Email"
                        className="form-control"
                        required
                    />
                </div>
                <div className="mb-3">
                    <input
                        type="tel"
                        value={phonenumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        placeholder="Phone Number"
                        className="form-control"
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Add User</button>
                <div className="mt-3 text-primary">{feedback}</div>
            </form>
        </div>
    );
};

export default UserForm;
